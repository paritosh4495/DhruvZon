package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.order.OrderRequestDTO;
import com.Ecommerce.dhruvzon.dto.order.OrderResponseDTO;
import com.Ecommerce.dhruvzon.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDTO toOrderResponseDTO(Order order);


    Order toOrder(OrderRequestDTO orderRequestDTO);

}
