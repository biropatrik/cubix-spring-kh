package hu.cubix.catalogservice.patrik.mapper;

import hu.cubix.catalogservice.patrik.model.CategoryDto;
import hu.cubix.catalogservice.patrik.model.HistoryDataProductDto;
import hu.cubix.catalogservice.patrik.model.ProductDto;
import hu.cubix.catalogservice.patrik.model.Category;
import hu.cubix.catalogservice.patrik.model.HistoryData;
import hu.cubix.catalogservice.patrik.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public ProductDto productToDto(Product product);

    public Product dtoToProduct(ProductDto productDto);

    List<ProductDto> productsToDtos(List<Product> products);

    @Mapping(target = "products", ignore = true)
    public CategoryDto categoryDto(Category category);

    @InheritInverseConfiguration
    public Category dtoToCategory(CategoryDto categoryDto);

    List<ProductDto> productsToDtos(Iterable<Product> products);

    List<HistoryData<ProductDto>> productsHistoryToDtos(List<HistoryData<Product>> history);

    List<HistoryDataProductDto> productsHistoryToHistoryDataProductDtos(List<HistoryData<Product>> history);

    default OffsetDateTime dateToOffsetDateTime(Date date) {
        return OffsetDateTime.ofInstant(date.toInstant(), ZoneId.of("Z"));
    }
}
