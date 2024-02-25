package hu.cubix.catalogservice.patrik.controller;

import hu.cubix.catalogservice.patrik.api.ProductControllerApi;
import hu.cubix.catalogservice.patrik.api.model.ProductDto;
import hu.cubix.catalogservice.patrik.exception.DifferentProductIdsException;
import hu.cubix.catalogservice.patrik.mapper.ProductMapper;
import hu.cubix.catalogservice.patrik.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return  ResponseEntity.ok(
                    productMapper.productsToDtos(
                            productService.findAll()));
    }

    @Override
    public ResponseEntity<ProductDto> addProduct(ProductDto productDto) {
        return ResponseEntity.ok(
                    productMapper.productToDto(
                            productService.create(
                                    productMapper.dtoToProduct(productDto))));
    }

    @Override
    public ResponseEntity<ProductDto> update(Integer id, ProductDto productDto) {
        if (productDto.getId() != null && !Objects.equals(productDto.getId(), id)) {
            throw new DifferentProductIdsException();
        }
        productDto.setId(id);

        return ResponseEntity.ok(
                    productMapper.productToDto(
                            productService.update(
                                    productMapper.dtoToProduct(productDto))));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
