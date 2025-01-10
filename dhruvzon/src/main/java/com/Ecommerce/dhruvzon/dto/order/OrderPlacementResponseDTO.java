package com.Ecommerce.dhruvzon.dto.order;

import lombok.Data;

@Data
public class OrderPlacementResponseDTO {
    private Long orderId;
    private String status;
    private String message;
}
