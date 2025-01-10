package com.Ecommerce.dhruvzon.dto.order;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderDraftResponseDTO {
    private Long orderId;
    private List<OrderItemResponseDTO> orderItems;
    private String shippingAddress;
    private String paymentMethod;
    private String status;
}
