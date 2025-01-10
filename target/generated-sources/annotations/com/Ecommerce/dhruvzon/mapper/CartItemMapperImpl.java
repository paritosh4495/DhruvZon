package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.cartItem.CartItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.cartItem.CartItemResponseDTO;
import com.Ecommerce.dhruvzon.model.CartItem;
import com.Ecommerce.dhruvzon.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-10T09:41:57+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemResponseDTO toCartItemResponseDTO(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();

        cartItemResponseDTO.setProductId( cartItemProductId( cartItem ) );
        cartItemResponseDTO.setId( cartItem.getId() );
        cartItemResponseDTO.setQuantity( cartItem.getQuantity() );
        cartItemResponseDTO.setPrice( cartItem.getPrice() );
        cartItemResponseDTO.setSubtotal( cartItem.getSubtotal() );

        return cartItemResponseDTO;
    }

    @Override
    public CartItem toCartItem(CartItemRequestDTO cartItemRequestDTO) {
        if ( cartItemRequestDTO == null ) {
            return null;
        }

        CartItem cartItem = new CartItem();

        cartItem.setQuantity( cartItemRequestDTO.getQuantity() );

        return cartItem;
    }

    private Long cartItemProductId(CartItem cartItem) {
        Product product = cartItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }
}
