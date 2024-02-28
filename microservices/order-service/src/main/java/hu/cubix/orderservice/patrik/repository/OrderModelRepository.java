package hu.cubix.orderservice.patrik.repository;

import hu.cubix.orderservice.patrik.model.OrderModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderModelRepository extends JpaRepository<OrderModel, Long> {

    @EntityGraph(attributePaths = {"address", "productWithInfos", "productWithInfos.product"})
    @Query("SELECT o FROM OrderModel o WHERE o.username = :username")
    List<OrderModel> findByUsername(String username);

    @EntityGraph(attributePaths = {"address", "productWithInfos", "productWithInfos.product"})
    @Query("SELECT o FROM OrderModel o WHERE o.id = :id")
    Optional<OrderModel> findById(Long id);

    Optional<OrderModel> findByShipmentId(Long id);
}
