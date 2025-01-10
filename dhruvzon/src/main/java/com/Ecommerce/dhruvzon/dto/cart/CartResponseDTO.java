package com.Ecommerce.dhruvzon.dto.cart;

import com.Ecommerce.dhruvzon.dto.cartItem.CartItemResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartResponseDTO {

    private Long id;
    private Long userId;
    private List<CartItemResponseDTO> cartItems;
    private BigDecimal totalPrice;

}
