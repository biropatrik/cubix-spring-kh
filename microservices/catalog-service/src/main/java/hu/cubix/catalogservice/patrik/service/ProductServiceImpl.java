package hu.cubix.catalogservice.patrik.service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import hu.cubix.catalogservice.patrik.exception.ProductAlreadyAddedException;
import hu.cubix.catalogservice.patrik.exception.ProductNotFoundException;
import hu.cubix.catalogservice.patrik.model.Product;
import hu.cubix.catalogservice.patrik.model.QProduct;
import hu.cubix.catalogservice.patrik.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAllWithCategory();
    }

    @Override
    @Transactional
    public Product create(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new ProductAlreadyAddedException();
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException();
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Iterable<Product> search(Predicate predicate, Pageable pageable, Integer fromPrice, Integer toPrice) {
        ArrayList<Predicate> predicates = new ArrayList<>();
        predicates.add(predicate);

        QProduct product = QProduct.product;
        if (fromPrice != null) {
            predicates.add(product.price.gt(fromPrice));
        }
        if (toPrice != null) {
            predicates.add(product.price.lt(toPrice));
        }

        return  productRepository.findAll(ExpressionUtils.allOf(predicates), pageable);
    }
}
