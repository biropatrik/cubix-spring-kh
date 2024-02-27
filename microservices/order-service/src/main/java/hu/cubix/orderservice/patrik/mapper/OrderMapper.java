package hu.cubix.orderservice.patrik.mapper;

import hu.cubix.catalogservice.patrik.model.ProductDto;
import hu.cubix.orderservice.patrik.dto.OrderDto;
import hu.cubix.orderservice.patrik.dto.ProductWithInfoDto;
import hu.cubix.orderservice.patrik.model.OrderModel;
import hu.cubix.orderservice.patrik.model.OrderedProduct;
import hu.cubix.orderservice.patrik.model.ProductWithInfo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    public OrderDto orderToDto(OrderModel order);

    public OrderModel dtoToOrder(OrderDto orderDto);

    public List<OrderDto> ordersToDtos(List<OrderModel> orders);

    @Mapping(target = "order", ignore = true)
    public ProductWithInfoDto productWithInfoToProductWithInfoDto(ProductWithInfo productWithInfo);

    @Mapping(target = "category", ignore = true)
    public ProductDto productToDto(OrderedProduct product);

    @InheritInverseConfiguration
    public OrderedProduct dtoToProduct(ProductDto productDto);
}
