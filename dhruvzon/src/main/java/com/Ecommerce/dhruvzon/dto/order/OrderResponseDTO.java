package com.Ecommerce.dhruvzon.dto.order;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import com.Ecommerce.dhruvzon.enums.OrderStatus;
import com.Ecommerce.dhruvzon.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDTO> orderItems;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String paymentDetails;
    private String adress;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
}
