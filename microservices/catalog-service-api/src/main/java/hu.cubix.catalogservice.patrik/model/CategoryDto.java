package hu.cubix.catalogservice.patrik.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CategoryDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-26T21:26:59.725292900+01:00[Europe/Budapest]")
public class CategoryDto {

  @JsonProperty("name")
  private String name;

  @JsonProperty("products")
  @Valid
  private List<ProductDto> products = null;

  public CategoryDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @Size(min = 3)
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CategoryDto products(List<ProductDto> products) {
    this.products = products;
    return this;
  }

  public CategoryDto addProductsItem(ProductDto productsItem) {
    if (this.products == null) {
      this.products = new ArrayList<>();
    }
    this.products.add(productsItem);
    return this;
  }

  /**
   * Get products
   * @return products
  */
  @Valid 
  @Schema(name = "products", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public List<ProductDto> getProducts() {
    return products;
  }

  public void setProducts(List<ProductDto> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryDto categoryDto = (CategoryDto) o;
    return Objects.equals(this.name, categoryDto.name) &&
        Objects.equals(this.products, categoryDto.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, products);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CategoryDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

