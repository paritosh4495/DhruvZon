package com.Ecommerce.dhruvzon.mapper;

import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemRequestDTO;
import com.Ecommerce.dhruvzon.dto.orderItem.OrderItemResponseDTO;
import com.Ecommerce.dhruvzon.model.OrderItem;
import com.Ecommerce.dhruvzon.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-11T13:28:34+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toOrderItem(OrderItemRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setProduct( orderItemRequestDTOToProduct( dto ) );
        orderItem.setQuantity( dto.getQuantity() );

        return orderItem;
    }

    @Override
    public OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();

        orderItemResponseDTO.setProductId( orderItemProductId( orderItem ) );
        orderItemResponseDTO.setProductName( orderItemProductName( orderItem ) );
        orderItemResponseDTO.setPrice( orderItem.getPriceSnapshot() );
        orderItemResponseDTO.setQuantity( orderItem.getQuantity() );

        return orderItemResponseDTO;
    }

    protected Product orderItemRequestDTOToProduct(OrderItemRequestDTO orderItemRequestDTO) {
        if ( orderItemRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( orderItemRequestDTO.getProductId() );

        return product;
    }

    private Long orderItemProductId(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getId();
    }

    private String orderItemProductName(OrderItem orderItem) {
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        return product.getName();
    }
}
