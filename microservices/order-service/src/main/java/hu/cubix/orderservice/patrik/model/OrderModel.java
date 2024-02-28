package hu.cubix.orderservice.patrik.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class OrderModel {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include()
    private long id;

    private Long shipmentId;

    private String username;

    private Status status;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "order")
    private List<ProductWithInfo> productWithInfos;
}
