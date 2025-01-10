package com.Ecommerce.dhruvzon.repository;

import com.Ecommerce.dhruvzon.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
