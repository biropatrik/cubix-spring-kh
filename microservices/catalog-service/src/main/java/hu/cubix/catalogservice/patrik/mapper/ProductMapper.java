package hu.cubix.catalogservice.patrik.mapper;

import hu.cubix.catalogservice.patrik.api.model.ProductDto;
import hu.cubix.catalogservice.patrik.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public ProductDto productToDto(Product product);

    public Product dtoToProduct(ProductDto productDto);

    List<ProductDto> productsToDtos(List<Product> products);
}
