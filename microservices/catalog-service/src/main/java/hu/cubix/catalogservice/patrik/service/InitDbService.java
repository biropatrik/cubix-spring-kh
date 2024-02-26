package hu.cubix.catalogservice.patrik.service;

import hu.cubix.catalogservice.patrik.model.Category;
import hu.cubix.catalogservice.patrik.model.Product;
import hu.cubix.catalogservice.patrik.repository.CategoryRepository;
import hu.cubix.catalogservice.patrik.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InitDbService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void deleteDb() {
        productRepository.deleteAllInBatch();
        categoryRepository.deleteAllInBatch();
    }

    @Transactional
    public void deleteAudTables() {
        jdbcTemplate.update("DELETE FROM category_aud");
        jdbcTemplate.update("DELETE FROM product_aud");
    }

    @Transactional
    public void initDb() {
        Category electronics = categoryRepository.save(Category.builder().name("Electronics").build());
        Category sports = categoryRepository.save(Category.builder().name("Sports").build());

        productRepository.save(Product.builder().name("smarthphone").price(250).category(electronics).build());
        productRepository.save(Product.builder().name("camera").price(500).category(electronics).build());
        productRepository.save(Product.builder().name("tv").price(155).category(electronics).build());

        productRepository.save(Product.builder().name("bicycle").price(325).category(sports).build());
        productRepository.save(Product.builder().name("dumbbell").price(50).category(sports).build());
    }
}
