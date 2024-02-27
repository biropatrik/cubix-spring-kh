package hu.cubix.orderservice.patrik.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class OrderedProduct {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private String name;

    private int price;
}
