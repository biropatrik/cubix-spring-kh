package hu.cubix.shipperservice.patrik.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ShippableProduct {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private String name;

    private int count;
}
