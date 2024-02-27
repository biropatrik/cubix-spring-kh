package hu.cubix.orderservice.patrik.repository;

import hu.cubix.orderservice.patrik.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
