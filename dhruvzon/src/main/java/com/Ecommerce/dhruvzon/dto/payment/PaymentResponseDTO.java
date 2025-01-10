package com.Ecommerce.dhruvzon.dto.payment;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long orderId;
    private String paymentStatus;
    private String transactionId;
    private String message;
}
