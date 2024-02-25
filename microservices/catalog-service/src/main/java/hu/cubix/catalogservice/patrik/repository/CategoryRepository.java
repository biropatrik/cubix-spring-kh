package hu.cubix.catalogservice.patrik.repository;

import hu.cubix.catalogservice.patrik.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"products"})
    @Query("SELECT c FROM Category c")
    List<Category> findAllWithProducts();
}
