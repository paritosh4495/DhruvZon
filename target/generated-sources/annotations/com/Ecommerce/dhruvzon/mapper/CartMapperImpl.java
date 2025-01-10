package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.cart.CartRequestDTO;
import com.Ecommerce.dhruvzon.dto.cart.CartResponseDTO;
import com.Ecommerce.dhruvzon.dto.cartItem.CartItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.cartItem.CartItemResponseDTO;
import com.Ecommerce.dhruvzon.model.Cart;
import com.Ecommerce.dhruvzon.model.CartItem;
import com.Ecommerce.dhruvzon.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-10T10:18:28+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public CartResponseDTO toCartResponseDTO(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponseDTO cartResponseDTO = new CartResponseDTO();

        cartResponseDTO.setUserId( cartUserId( cart ) );
        cartResponseDTO.setId( cart.getId() );
        cartResponseDTO.setCartItems( cartItemListToCartItemResponseDTOList( cart.getCartItems() ) );
        cartResponseDTO.setTotalPrice( cart.getTotalPrice() );

        return cartResponseDTO;
    }

    @Override
    public Cart toCart(CartRequestDTO cartRequestDTO) {
        if ( cartRequestDTO == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setCartItems( cartItemRequestDTOListToCartItemList( cartRequestDTO.getCartItems() ) );

        return cart;
    }

    private Long cartUserId(Cart cart) {
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected List<CartItemResponseDTO> cartItemListToCartItemResponseDTOList(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemResponseDTO> list1 = new ArrayList<CartItemResponseDTO>( list.size() );
        for ( CartItem cartItem : list ) {
            list1.add( cartItemMapper.toCartItemResponseDTO( cartItem ) );
        }

        return list1;
    }

    protected List<CartItem> cartItemRequestDTOListToCartItemList(List<CartItemRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItem> list1 = new ArrayList<CartItem>( list.size() );
        for ( CartItemRequestDTO cartItemRequestDTO : list ) {
            list1.add( cartItemMapper.toCartItem( cartItemRequestDTO ) );
        }

        return list1;
    }
}
