package com.Ecommerce.dhruvzon.dto.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderPlacementRequestDTO {
    @NotBlank(message = "Order ID is required")
    private Long orderId;
}
