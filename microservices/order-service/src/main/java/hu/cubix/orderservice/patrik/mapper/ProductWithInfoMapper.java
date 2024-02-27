package hu.cubix.orderservice.patrik.mapper;

import hu.cubix.catalogservice.patrik.model.ProductDto;
import hu.cubix.orderservice.patrik.dto.ProductWithInfoDto;
import hu.cubix.orderservice.patrik.model.OrderedProduct;
import hu.cubix.orderservice.patrik.model.ProductWithInfo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductWithInfoMapper {

    public ProductWithInfoDto productWithInfoToDto(ProductWithInfo productWithInfo);

    public ProductWithInfo dtoToProductWithInfo(ProductWithInfoDto productWithInfo);

    @Mapping(target = "order", ignore = true)
    public List<ProductWithInfoDto> productInfosToDtos(List<ProductWithInfo> productWithInfo);

    @InheritInverseConfiguration
    public List<ProductWithInfo> dtosToProductInfos(List<ProductWithInfoDto> productWithInfoDto);

    @Mapping(target = "category", ignore = true)
    public ProductDto productToDto(OrderedProduct product);

    @InheritInverseConfiguration
    public OrderedProduct dtoToProduct(ProductDto productDto);
}
