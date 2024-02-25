package hu.cubix.catalogservice.patrik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Category {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
