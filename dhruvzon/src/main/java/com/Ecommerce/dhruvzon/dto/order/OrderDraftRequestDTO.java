package com.Ecommerce.dhruvzon.dto.order;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDraftRequestDTO {

    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemRequestDTO> orderItems;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

}
