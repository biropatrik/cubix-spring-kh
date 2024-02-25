package hu.cubix.catalogservice.patrik.repository;

import hu.cubix.catalogservice.patrik.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
