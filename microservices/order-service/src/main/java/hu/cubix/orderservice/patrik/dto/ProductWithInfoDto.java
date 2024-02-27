package hu.cubix.orderservice.patrik.dto;

import hu.cubix.catalogservice.patrik.model.ProductDto;
import lombok.Data;

@Data
public class ProductWithInfoDto {

    private long id;

    private int count;

    private ProductDto product;

    private OrderDto order;
}
