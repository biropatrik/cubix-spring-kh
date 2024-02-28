package hu.cubix.shipperservice.patrik.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Shipment {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private Address fromAddress;

    private Address toAddress;

    private List<ShippableProduct> products;
}
