package com.Ecommerce.dhruvzon.controller;

import com.Ecommerce.dhruvzon.dto.order.*;
import com.Ecommerce.dhruvzon.response.ApiResponse;
import com.Ecommerce.dhruvzon.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Convert Cart to Draft Order
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/draft")
    public ResponseEntity<ApiResponse<OrderDraftResponseDTO>> convertCartToDraftOrder() {
        OrderDraftResponseDTO draftOrder = orderService.convertCartToDraftOrder();
        ApiResponse<OrderDraftResponseDTO> response = new ApiResponse<>(draftOrder, "Draft order created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update Draft Order
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/draft/{orderId}")
    public ResponseEntity<ApiResponse<OrderDraftResponseDTO>> updateDraftOrder(
            @PathVariable Long orderId,
            @RequestBody OrderDraftRequestDTO orderDraftRequestDTO) {
        OrderDraftResponseDTO updatedDraftOrder = orderService.updateDraftOrder(orderId, orderDraftRequestDTO);
        ApiResponse<OrderDraftResponseDTO> response = new ApiResponse<>(updatedDraftOrder, "Draft order updated successfully");
        return ResponseEntity.ok(response);
    }

    // Remove Item from Draft Order
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/draft/{orderId}/item/{productId}")
    public ResponseEntity<ApiResponse<Void>> removeItemFromDraftOrder(
            @PathVariable Long orderId,
            @PathVariable Long productId) {
        orderService.removeItemFromDraftOrder(orderId, productId);
        ApiResponse<Void> response = new ApiResponse<>(null, "Item removed from draft order successfully");
        return ResponseEntity.ok(response);
    }

    // Update Delivery Address
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/draft/{orderId}/address")
    public ResponseEntity<ApiResponse<OrderDraftResponseDTO>> updateDeliveryAddress(
            @PathVariable Long orderId,
            @RequestBody String deliveryAddress) {
        OrderDraftResponseDTO updatedDraftOrder = orderService.updateDeliveryAddress(orderId, deliveryAddress);
        ApiResponse<OrderDraftResponseDTO> response = new ApiResponse<>(updatedDraftOrder, "Delivery address updated successfully");
        return ResponseEntity.ok(response);
    }

    // Place Order
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/place")
    public ResponseEntity<ApiResponse<OrderPlacementResponseDTO>> placeOrder(
            @RequestBody OrderPlacementRequestDTO orderPlacementRequestDTO) {
        OrderPlacementResponseDTO placedOrder = orderService.placeOrder(orderPlacementRequestDTO);
        ApiResponse<OrderPlacementResponseDTO> response = new ApiResponse<>(placedOrder, "Order placed successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Cancel Order
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<ApiResponse<OrderDetailResponseDTO>> cancelOrder(@PathVariable Long orderId) {
        OrderDetailResponseDTO canceledOrder = orderService.cancelOrder(orderId);
        ApiResponse<OrderDetailResponseDTO> response = new ApiResponse<>(canceledOrder, "Order canceled successfully");
        return ResponseEntity.ok(response);
    }

    // Get Order Status
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<OrderDetailResponseDTO>> getOrderStatus(@PathVariable Long orderId) {
        OrderDetailResponseDTO orderStatus = orderService.getOrderStatus(orderId);
        ApiResponse<OrderDetailResponseDTO> response = new ApiResponse<>(orderStatus, "Order status retrieved successfully");
        return ResponseEntity.ok(response);
    }

    // Get Order History
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/history/{userId}")
    public ResponseEntity<ApiResponse<List<OrderSummaryDTO>>> getOrderHistory(
            @PathVariable Long userId
    ) {
        List<OrderSummaryDTO> orderHistory = orderService.getOrderHistory(userId);
        ApiResponse<List<OrderSummaryDTO>> response = new ApiResponse<>(orderHistory, "Order history retrieved successfully");
        return ResponseEntity.ok(response);
    }


}
