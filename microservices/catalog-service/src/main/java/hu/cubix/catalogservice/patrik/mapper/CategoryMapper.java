package hu.cubix.catalogservice.patrik.mapper;

import hu.cubix.catalogservice.patrik.api.model.CategoryDto;
import hu.cubix.catalogservice.patrik.api.model.ProductDto;
import hu.cubix.catalogservice.patrik.model.Category;
import hu.cubix.catalogservice.patrik.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public CategoryDto categoryDto(Category category);

    public Category dtoToCategory(CategoryDto categoryDto);

    public List<CategoryDto> categoriesToDtos(List<Category> categories);

    @Mapping(target = "category", ignore = true)
    public ProductDto productToDto(Product product);

    @InheritInverseConfiguration
    public Product dtoToProduct(ProductDto productDto);
}
