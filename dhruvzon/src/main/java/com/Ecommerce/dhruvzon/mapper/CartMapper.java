package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.cart.CartRequestDTO;
import com.Ecommerce.dhruvzon.dto.cart.CartResponseDTO;
import com.Ecommerce.dhruvzon.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartItemMapper.class)
public interface CartMapper {

    @Mapping(source = "user.id", target = "userId")
    CartResponseDTO toCartResponseDTO(Cart cart);

    Cart toCart(CartRequestDTO cartRequestDTO);

}
