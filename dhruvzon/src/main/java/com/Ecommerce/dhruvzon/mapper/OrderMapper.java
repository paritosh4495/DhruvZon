package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.order.*;
import com.Ecommerce.dhruvzon.dto.payment.PaymentResponseDTO;
import com.Ecommerce.dhruvzon.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {


    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "status", constant = "DRAFT")
    Order toDraftEntity(OrderDraftRequestDTO dto);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "shippingAddress", source = "address")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    OrderDraftResponseDTO toDraftResponseDTO(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", source = "status")
    OrderPlacementResponseDTO toPlacementResponseDTO(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "paymentStatus", source = "paymentStatus")
    @Mapping(target = "paymentDetails", source = "paymentDetails")
    PaymentResponseDTO toPaymentResponseDTO(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "paymentStatus", source = "paymentStatus")
    @Mapping(target = "shippingAddress", source = "address")
    @Mapping(target = "createdDate", source = "createdDate")
    OrderSummaryDTO toSummaryDTO(Order order);

    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "shippingAddress", source = "address")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "paymentStatus", source = "paymentStatus")
    @Mapping(target = "paymentDetails", source = "paymentDetails")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "modifiedDate", source = "modifiedDate")
    OrderDetailResponseDTO toDetailResponseDTO(Order order);

}
