package com.Ecommerce.dhruvzon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_CartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    @Column(nullable = false)
    @Min(1)
    private Integer quantity;

    @Column(nullable = false)
    @Positive
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal finalPrice;

    @PrePersist
    @PreUpdate
    private void calculatePrices() {
        // Ensure discount does not exceed unit price
        BigDecimal validDiscount = discount.min(price);

        // Calculate final price per unit
        finalPrice = price.subtract(validDiscount);

        // Calculate subtotal based on final price and quantity
        subtotal = finalPrice.multiply(BigDecimal.valueOf(quantity));
    }



}
