package hu.cubix.catalogservice.patrik.controller;

import com.querydsl.core.types.Predicate;
import hu.cubix.catalogservice.patrik.api.ProductControllerApi;
import hu.cubix.catalogservice.patrik.api.model.HistoryDataProductDto;
import hu.cubix.catalogservice.patrik.api.model.ProductDto;
import hu.cubix.catalogservice.patrik.exception.DifferentProductIdsException;
import hu.cubix.catalogservice.patrik.mapper.ProductMapper;
import hu.cubix.catalogservice.patrik.model.Product;
import hu.cubix.catalogservice.patrik.service.ProductService;
import hu.cubix.catalogservice.patrik.web.MethodArgumentResolverHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final MethodArgumentResolverHelper resolverHelper;
    private final NativeWebRequest request;

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
    public ResponseEntity<List<ProductDto>> search(Integer fromPrice, Integer toPrice, Integer page, Integer size, List<String> sort) {
        Pageable pageable = resolverHelper.createPageable(this.getClass(), "configPageable", request);
        Predicate predicate = resolverHelper.createPredicate(this.getClass(), "configurePredicate", request);

        return ResponseEntity.ok(
                productMapper.productsToDtos(
                        productService.search(predicate, pageable, fromPrice, toPrice)));
    }

    public void configPageable(@SortDefault("id") Pageable pageable) {}

    public void configurePredicate(@QuerydslPredicate(root = Product.class) Predicate predicate) {}

    @Override
    public ResponseEntity<List<HistoryDataProductDto>> getHistory(Integer id) {
        return ResponseEntity.ok(
                productMapper.productsHistoryToHistoryDataProductDtos(productService.getHistoryById(id)));
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
