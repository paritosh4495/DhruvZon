package com.Ecommerce.dhruvzon.service.cart;

import com.Ecommerce.dhruvzon.dto.cart.CartRequestDTO;
import com.Ecommerce.dhruvzon.dto.cart.CartResponseDTO;
import com.Ecommerce.dhruvzon.model.Cart;

public interface CartService {


    CartResponseDTO addItemsToCart(CartRequestDTO cartRequestDTO);



    void removeItemFromCart(Long cartItemId);

    // for admin only !
    CartResponseDTO getCartByUserId(Long userId);

    void clearCart();

    void calculateTotalPrice(Cart cart);


    // boolean validateCartForCheckout();




}
