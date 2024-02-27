package hu.cubix.orderservice.patrik.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProductWithInfo {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private int count;

    @ManyToOne
    private OrderedProduct product;

    @ManyToOne
    private OrderModel order;
}
