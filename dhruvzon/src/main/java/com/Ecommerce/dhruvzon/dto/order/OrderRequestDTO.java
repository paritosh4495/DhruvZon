package com.Ecommerce.dhruvzon.dto.order;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Order items are required")
    private List<OrderItemRequestDTO> orderItems;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

}
