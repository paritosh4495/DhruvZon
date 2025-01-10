package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import com.Ecommerce.dhruvzon.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id", target = "productId")
    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem);


    OrderItem toOrderItem(OrderItemRequestDTO orderItemRequestDTO);
}
