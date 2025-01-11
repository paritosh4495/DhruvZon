package com.Ecommerce.dhruvzon.service.order;

import com.Ecommerce.dhruvzon.dto.order.*;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.payment.PaymentRequestDTO;
import com.Ecommerce.dhruvzon.dto.payment.PaymentResponseDTO;
import com.Ecommerce.dhruvzon.enums.OrderStatus;
import com.Ecommerce.dhruvzon.enums.PaymentMethod;
import com.Ecommerce.dhruvzon.enums.PaymentStatus;
import com.Ecommerce.dhruvzon.exception.*;
import com.Ecommerce.dhruvzon.mapper.OrderItemMapper;
import com.Ecommerce.dhruvzon.mapper.OrderMapper;
import com.Ecommerce.dhruvzon.model.*;
import com.Ecommerce.dhruvzon.repository.*;
import com.Ecommerce.dhruvzon.security.user.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderDraftResponseDTO convertCartToDraftOrder() {
        Long userId = getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order();
        order.setIsDraft(true);
        order.setUser(user);
        order.setStatus(OrderStatus.DRAFT);
        order.setAddress("Default Address"); // This should be updated later
        order.setPaymentMethod(PaymentMethod.UPI); // This should be updated later
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceSnapshot(cartItem.getProduct().getPrice());
            orderItem.setTotalPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            totalAmount = totalAmount.add(orderItem.getTotalPrice());
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalAmount);

        orderRepository.save(order);
        return orderMapper.toDraftResponseDTO(order);
    }

    @Override
    public OrderDraftResponseDTO updateDraftOrder(Long orderId, OrderDraftRequestDTO orderDraftRequestDTO) {
        Order draftOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        if (!draftOrder.getIsDraft()) {
            throw new IllegalOperationException("You cannot update this Order");
        }

        Long userId = getCurrentUserId();
        if (!Objects.equals(draftOrder.getUser().getId(), userId)) {
            throw new IllegalOperationException("You are not authorized to update this Order");
        }

        // Clear existing order items
        draftOrder.getOrderItems().clear();

        List<OrderItem> updatedOrderItems = new ArrayList<>();
        for (OrderItemRequestDTO itemRequest : orderDraftRequestDTO.getOrderItems()) {
            Long productId = itemRequest.getProductId();
            Integer quantity = itemRequest.getQuantity();

            if (quantity > 0) {
                OrderItem newItem = new OrderItem();
                newItem.setProduct(productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product Not Found")));
                newItem.setQuantity(quantity);
                newItem.setPriceSnapshot(newItem.getProduct().getPrice());
                newItem.setTotalPrice(newItem.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)));
                newItem.setOrder(draftOrder);
                updatedOrderItems.add(newItem);
            }
        }

        // Set the updated items to the order
        draftOrder.setOrderItems(updatedOrderItems);

        // Recalculate totals
        BigDecimal totalAmount = updatedOrderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        draftOrder.setTotalPrice(totalAmount);

        // Save the updated order
        orderRepository.save(draftOrder);

        // Convert to OrderDraftResponseDTO
        return orderMapper.toDraftResponseDTO(draftOrder);
    }


    @Override
    public void removeItemFromDraftOrder(Long orderId, Long productId) {
        Order draftOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        if (!draftOrder.getIsDraft()) {
            throw new IllegalOperationException("You cannot remove from this Order");
        }

        Long userId = getCurrentUserId();
        if (!Objects.equals(draftOrder.getUser().getId(), userId)) {
            throw new IllegalOperationException("You are not authorized to remove from this Order");
        }

        List<OrderItem> orderItems = draftOrder.getOrderItems();
        OrderItem itemToRemove = null;

        for (OrderItem item : orderItems) {
            if (item.getProduct().getId().equals(productId)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            orderItems.remove(itemToRemove);
            orderItemRepository.delete(itemToRemove);

            BigDecimal totalAmount = orderItems.stream()
                    .map(OrderItem::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            draftOrder.setTotalPrice(totalAmount);

            orderRepository.save(draftOrder);

        }else {
            throw new ItemNotFoundException("Item not found in the order");
        }

    }

    @Override
    public OrderDraftResponseDTO updateDeliveryAddress(Long orderId, String deliveryAddress) {
        Order draftOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        if (!draftOrder.getIsDraft()) {
            throw new IllegalOperationException("You cannot update this Order");
        }

        Long userId = getCurrentUserId();
        if (!Objects.equals(draftOrder.getUser().getId(), userId)) {
            throw new IllegalOperationException("You are not authorized to update this Order");
        }

        draftOrder.setAddress(deliveryAddress);
        Order savedOrder = orderRepository.save(draftOrder);
        return orderMapper.toDraftResponseDTO(savedOrder);
    }

    @Override
    public OrderPlacementResponseDTO placeOrder(OrderPlacementRequestDTO orderPlacementRequestDTO) {
        Order draftOrder = orderRepository.findById(orderPlacementRequestDTO.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        if (!draftOrder.getIsDraft()) {
            throw new IllegalOperationException("You cannot place this Order");
        }

        Long userId = getCurrentUserId();
        if (!Objects.equals(draftOrder.getUser().getId(), userId)) {
            throw new IllegalOperationException("You are not authorized to place this Order");
        }

        if (!validateStock(draftOrder.getId())) {
            throw new IllegalOperationException("Stock validation failed");
        }

        if (!confirmShippingDetails(draftOrder.getId())) {
            throw new IllegalOperationException("Shipping details validation failed");
        }

        // Deduct stock
        deductStock(draftOrder.getId());

        draftOrder.setIsDraft(false);
        draftOrder.setStatus(OrderStatus.CONFIRMED);
        draftOrder.setShippingMethod("CAR");
        draftOrder.setDeliveryInstructions("Inst");


        draftOrder.setPaymentStatus(PaymentStatus.COMPLETED);

        Order savedOrder = orderRepository.save(draftOrder);

        return orderMapper.toPlacementResponseDTO(savedOrder);
    }

    @Override
    public boolean validateStock(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        List<OrderItem> items = order.getOrderItems();

        for (OrderItem item : items) {
            if( item.getQuantity() > item.getProduct().getStockQuantity() ){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean confirmShippingDetails(Long orderId) {
        return true;
    }

    @Override
    public PaymentResponseDTO processPayment(Long orderId, PaymentRequestDTO paymentDTO) {
        return null;
    }

    @Override
    public void handlePaymentCallback(String paymentIntentId, String status) {

    }

    @Override
    public OrderDetailResponseDTO confirmOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        order.setStatus(OrderStatus.CONFIRMED);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDetailResponseDTO(savedOrder);
    }

    @Override
    public void deductStock(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

       if(validateStock(order.getId())){
           List<OrderItem> items = order.getOrderItems();
           for (OrderItem item : items) {
               item.getProduct().setStockQuantity(item.getProduct().getStockQuantity() - item.getQuantity());
           }
       }
       else{
           throw new IllegalArgumentException("Order Value Cannot be greater than the stock !");
       }

    }

    @Override
    public void initiateShipping(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        order.setStatus(OrderStatus.SHIPPED);
        orderRepository.save(order);
    }

    @Override
    public OrderDetailResponseDTO getOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        return orderMapper.toDetailResponseDTO(order);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        order.setStatus(OrderStatus.valueOf(status));
        orderRepository.save(order);
    }

    @Override
    public List<OrderSummaryDTO> getOrderHistory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found!"));
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper:: toSummaryDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponseDTO cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        // Restock items
        restockItems(order.getId());

        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDetailResponseDTO(savedOrder);
    }

    private void restockItems(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            productRepository.save(product);
        }
    }

    @Override
    public OrderDetailResponseDTO processReturn(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order Not Found!"));

        order.setStatus(OrderStatus.RETURNED);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDetailResponseDTO(savedOrder);
    }

    @Override
    public void processRefund(Long orderId) {

    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
