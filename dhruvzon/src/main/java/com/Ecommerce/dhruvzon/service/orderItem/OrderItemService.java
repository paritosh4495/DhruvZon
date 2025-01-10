package com.Ecommerce.dhruvzon.service.orderItem;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;

public interface OrderItemService {

    OrderItemResponseDTO addOrderItem(Long orderId, OrderItemRequestDTO orderItemRequestDTO);

    void removeOrderItem(Long orderId, Long orderItemId);

    OrderItemResponseDTO updateOrderItem(Long orderId, Long orderItemId, OrderItemRequestDTO orderItemRequestDTO);
}
