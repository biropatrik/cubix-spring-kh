package hu.cubix.catalogservice.patrik.service;

import hu.cubix.catalogservice.patrik.exception.ProductAlreadyAddedException;
import hu.cubix.catalogservice.patrik.exception.ProductNotFoundException;
import hu.cubix.catalogservice.patrik.model.Product;
import hu.cubix.catalogservice.patrik.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
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
}
