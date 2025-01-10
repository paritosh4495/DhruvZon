package com.Ecommerce.dhruvzon.service.order;


import com.Ecommerce.dhruvzon.dto.order.*;
import com.Ecommerce.dhruvzon.dto.payment.PaymentRequestDTO;
import com.Ecommerce.dhruvzon.dto.payment.PaymentResponseDTO;

import java.util.List;

public interface OrderService {

    // Cart To Order Draft Conversion
    OrderDraftResponseDTO convertCartToDraftOrder(); //We will take current logged in user
    OrderDraftResponseDTO updateDraftOrder(Long orderId, OrderDraftRequestDTO orderDraftRequestDTO);
    void removeItemFromDraftOrder(Long orderId, Long productId);
    OrderDraftResponseDTO updateDeliveryAddress(Long orderId, String deliveryAddress);

    // Order Placement

    OrderPlacementResponseDTO placeOrder(OrderPlacementRequestDTO orderPlacementRequestDTO);
    boolean validateStock(Long orderId);
    boolean confirmShippingDetails(Long orderId);

    // Payment Processing

    PaymentResponseDTO processPayment(Long orderId, PaymentRequestDTO paymentDTO);
    void handlePaymentCallback(String paymentIntentId, String status);

    // Order Confimation

    OrderDetailResponseDTO confirmOrder(Long orderId);
    void deductStock(Long orderId);
    void initiateShipping(Long orderId);

    // Order Tracking

    OrderDetailResponseDTO getOrderStatus(Long orderId);
    void updateOrderStatus(Long orderId, String status);

    // Order History
    List<OrderSummaryDTO> getOrderHistory(Long userId);


    // Order Cancellation & Returns
    OrderDetailResponseDTO cancelOrder(Long orderId);
    OrderDetailResponseDTO processReturn(Long orderId);
    void processRefund(Long orderId);


}
