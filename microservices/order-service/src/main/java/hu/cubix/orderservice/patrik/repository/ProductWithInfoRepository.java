package hu.cubix.orderservice.patrik.repository;

import hu.cubix.orderservice.patrik.model.ProductWithInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductWithInfoRepository extends JpaRepository<ProductWithInfo, Long> {
}
