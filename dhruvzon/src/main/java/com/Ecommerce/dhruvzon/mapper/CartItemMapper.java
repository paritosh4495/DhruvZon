package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.cartItem.CartItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.cartItem.CartItemResponseDTO;
import com.Ecommerce.dhruvzon.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "product.id", target = "productId")
    CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem);


    CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO);

}
