package com.Ecommerce.dhruvzon.dto.order;

import lombok.Data;

@Data
public class OrderSummaryDTO {
    private Long orderId;
    private String status;
    private String paymentStatus;
    private String shippingAddress;
    private String createdDate;
}
