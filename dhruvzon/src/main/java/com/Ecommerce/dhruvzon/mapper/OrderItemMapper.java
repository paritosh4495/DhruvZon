package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import com.Ecommerce.dhruvzon.model.OrderItem;
import com.Ecommerce.dhruvzon.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "product.id", source = "productId")
    OrderItem toOrderItem(OrderItemRequestDTO dto);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "price", source = "priceSnapshot")
    OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem);
}
