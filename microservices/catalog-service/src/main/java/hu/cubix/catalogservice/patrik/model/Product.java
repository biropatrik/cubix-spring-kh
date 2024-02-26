package hu.cubix.catalogservice.patrik.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.envers.Audited;

@Audited
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private String name;

    private int price;

    @ManyToOne
    private Category category;
}
