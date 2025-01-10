package com.Ecommerce.dhruvzon.dto.cart;

import com.Ecommerce.dhruvzon.dto.cartItem.CartItemRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class CartRequestDTO {

    private List<CartItemRequestDTO> cartItems;

}
