package com.Ecommerce.dhruvzon.dto.order;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailResponseDTO {
    private Long orderId;
    private List<OrderItemResponseDTO> orderItems;
    private String shippingAddress;
    private String status;
    private String paymentStatus;
    private String paymentDetails;
    private String createdDate;
    private String modifiedDate;
}
