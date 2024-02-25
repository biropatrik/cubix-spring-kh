package hu.cubix.catalogservice.patrik.service;

import hu.cubix.catalogservice.patrik.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product create(Product product);

    public Product update(Product product);

    public void deleteById(long id);
}
