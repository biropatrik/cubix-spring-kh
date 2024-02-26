package hu.cubix.catalogservice.patrik.service;

import com.querydsl.core.types.Predicate;
import hu.cubix.catalogservice.patrik.model.HistoryData;
import hu.cubix.catalogservice.patrik.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product create(Product product);

    public Product update(Product product);

    public void deleteById(long id);

    public Iterable<Product> search(Predicate predicate, Pageable pageable, Integer fromPrice, Integer toPrice);

    public List<HistoryData<Product>> getHistoryById(int id);
}
