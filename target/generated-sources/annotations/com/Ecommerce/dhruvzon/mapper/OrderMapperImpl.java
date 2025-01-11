package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.order.OrderDetailResponseDTO;
import com.Ecommerce.dhruvzon.dto.order.OrderDraftRequestDTO;
import com.Ecommerce.dhruvzon.dto.order.OrderDraftResponseDTO;
import com.Ecommerce.dhruvzon.dto.order.OrderPlacementResponseDTO;
import com.Ecommerce.dhruvzon.dto.order.OrderSummaryDTO;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import com.Ecommerce.dhruvzon.dto.payment.PaymentResponseDTO;
import com.Ecommerce.dhruvzon.enums.OrderStatus;
import com.Ecommerce.dhruvzon.enums.PaymentMethod;
import com.Ecommerce.dhruvzon.model.Order;
import com.Ecommerce.dhruvzon.model.OrderItem;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T13:28:34+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Order toDraftEntity(OrderDraftRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderItems( orderItemRequestDTOListToOrderItemList( dto.getOrderItems() ) );
        if ( dto.getPaymentMethod() != null ) {
            order.setPaymentMethod( Enum.valueOf( PaymentMethod.class, dto.getPaymentMethod() ) );
        }

        order.setStatus( OrderStatus.DRAFT );

        return order;
    }

    @Override
    public OrderDraftResponseDTO toDraftResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDraftResponseDTO orderDraftResponseDTO = new OrderDraftResponseDTO();

        orderDraftResponseDTO.setOrderId( order.getId() );
        if ( order.getStatus() != null ) {
            orderDraftResponseDTO.setStatus( order.getStatus().name() );
        }
        orderDraftResponseDTO.setShippingAddress( order.getAddress() );
        if ( order.getPaymentMethod() != null ) {
            orderDraftResponseDTO.setPaymentMethod( order.getPaymentMethod().name() );
        }
        orderDraftResponseDTO.setOrderItems( orderItemListToOrderItemResponseDTOList( order.getOrderItems() ) );

        return orderDraftResponseDTO;
    }

    @Override
    public OrderPlacementResponseDTO toPlacementResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderPlacementResponseDTO orderPlacementResponseDTO = new OrderPlacementResponseDTO();

        orderPlacementResponseDTO.setOrderId( order.getId() );
        if ( order.getStatus() != null ) {
            orderPlacementResponseDTO.setStatus( order.getStatus().name() );
        }

        return orderPlacementResponseDTO;
    }

    @Override
    public PaymentResponseDTO toPaymentResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();

        paymentResponseDTO.setOrderId( order.getId() );
        if ( order.getPaymentStatus() != null ) {
            paymentResponseDTO.setPaymentStatus( order.getPaymentStatus().name() );
        }

        return paymentResponseDTO;
    }

    @Override
    public OrderSummaryDTO toSummaryDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderSummaryDTO orderSummaryDTO = new OrderSummaryDTO();

        orderSummaryDTO.setOrderId( order.getId() );
        if ( order.getStatus() != null ) {
            orderSummaryDTO.setStatus( order.getStatus().name() );
        }
        if ( order.getPaymentStatus() != null ) {
            orderSummaryDTO.setPaymentStatus( order.getPaymentStatus().name() );
        }
        orderSummaryDTO.setShippingAddress( order.getAddress() );
        if ( order.getCreatedDate() != null ) {
            orderSummaryDTO.setCreatedDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( order.getCreatedDate() ) );
        }

        return orderSummaryDTO;
    }

    @Override
    public OrderDetailResponseDTO toDetailResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDetailResponseDTO orderDetailResponseDTO = new OrderDetailResponseDTO();

        orderDetailResponseDTO.setOrderId( order.getId() );
        orderDetailResponseDTO.setOrderItems( orderItemListToOrderItemResponseDTOList( order.getOrderItems() ) );
        orderDetailResponseDTO.setShippingAddress( order.getAddress() );
        if ( order.getStatus() != null ) {
            orderDetailResponseDTO.setStatus( order.getStatus().name() );
        }
        if ( order.getPaymentStatus() != null ) {
            orderDetailResponseDTO.setPaymentStatus( order.getPaymentStatus().name() );
        }
        orderDetailResponseDTO.setPaymentDetails( order.getPaymentDetails() );
        if ( order.getCreatedDate() != null ) {
            orderDetailResponseDTO.setCreatedDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( order.getCreatedDate() ) );
        }
        if ( order.getModifiedDate() != null ) {
            orderDetailResponseDTO.setModifiedDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( order.getModifiedDate() ) );
        }

        return orderDetailResponseDTO;
    }

    protected List<OrderItem> orderItemRequestDTOListToOrderItemList(List<OrderItemRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemRequestDTO orderItemRequestDTO : list ) {
            list1.add( orderItemMapper.toOrderItem( orderItemRequestDTO ) );
        }

        return list1;
    }

    protected List<OrderItemResponseDTO> orderItemListToOrderItemResponseDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemResponseDTO> list1 = new ArrayList<OrderItemResponseDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemMapper.toOrderItemResponseDTO( orderItem ) );
        }

        return list1;
    }
}
