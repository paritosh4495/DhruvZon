package com.Ecommerce.dhruvzon.service.cart;

import com.Ecommerce.dhruvzon.dto.cart.CartRequestDTO;
import com.Ecommerce.dhruvzon.dto.cart.CartResponseDTO;

import com.Ecommerce.dhruvzon.dto.cartItem.CartItemRequestDTO;
import com.Ecommerce.dhruvzon.exception.CartItemNotFoundException;
import com.Ecommerce.dhruvzon.exception.CartNotFoundException;
import com.Ecommerce.dhruvzon.exception.ProductNotFoundException;
import com.Ecommerce.dhruvzon.exception.UserNotFoundException;
import com.Ecommerce.dhruvzon.mapper.CartMapper;
import com.Ecommerce.dhruvzon.model.Cart;
import com.Ecommerce.dhruvzon.model.CartItem;
import com.Ecommerce.dhruvzon.model.Product;
import com.Ecommerce.dhruvzon.model.User;
import com.Ecommerce.dhruvzon.repository.CartItemRepository;
import com.Ecommerce.dhruvzon.repository.CartRepository;
import com.Ecommerce.dhruvzon.repository.ProductRepository;
import com.Ecommerce.dhruvzon.repository.UserRepository;
import com.Ecommerce.dhruvzon.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {


    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartResponseDTO addItemsToCart(CartRequestDTO cartRequestDTO) {
        Long userId = getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        if (cartRequestDTO == null) {
            throw new IllegalArgumentException("CartRequestDTO cannot be null");
        }

        // Fetch or create the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    newCart.setTotalPrice(BigDecimal.ZERO);
                    return cartRepository.save(newCart); // Save the new cart
                });

        for (CartItemRequestDTO cartItemRequestDTO : cartRequestDTO.getCartItems()) {
            Product product = productRepository.findById(cartItemRequestDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + cartItemRequestDTO.getProductId()));

            if (cartItemRequestDTO.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }
            if (product.getStockQuantity() < cartItemRequestDTO.getQuantity()) {
                throw new IllegalArgumentException("Requested quantity exceeds product stock.");
            }

            // Check if the product is already in the cart
            CartItem existingItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(cartItemRequestDTO.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (existingItem != null) {
                // Update the quantity and price for the existing cart item
                existingItem.setQuantity(existingItem.getQuantity() + cartItemRequestDTO.getQuantity());
            } else {
                // Add a new cart item
                CartItem cartItem = new CartItem();
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartItemRequestDTO.getQuantity());
                cartItem.setPrice(product.getPrice());
                cart.getCartItems().add(cartItem);
                cartItemRepository.save(cartItem); // Save the new cart item
            }
        }

        calculateTotalPrice(cart);
        cartRepository.save(cart); // Save the updated cart

        return cartMapper.toCartResponseDTO(cart);
    }

    @Override
    public void removeItemFromCart(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found with ID: " + cartItemId));

        Cart cart = cartItem.getCart();
        cart.getCartItems().remove(cartItem); // Remove from the collection
        cartItemRepository.delete(cartItem);
        calculateTotalPrice(cart);
        cartRepository.save(cart);

        logger.info("Removed item [{}] from cart for user [{}]", cartItemId, cart.getUser().getId());
    }

    @Override
    public CartResponseDTO getCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));

        logger.info("Retrieved cart for user [{}]", userId);
        return cartMapper.toCartResponseDTO(cart);
    }

    @Override
    public void clearCart() {
        Long userId = getCurrentUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));

        cartItemRepository.deleteByCartId(cart.getId());
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);

        logger.info("Cleared cart for user [{}]", userId);
    }

    @Override
    public void calculateTotalPrice(Cart cart) {
        BigDecimal totalPrice = cart.getCartItems().stream()
                .map(cartItem -> cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalPrice(totalPrice);
        logger.info("Calculated total price [{}] for cart [{}]", totalPrice, cart.getId());
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}
