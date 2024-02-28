package hu.cubix.orderservice.patrik.dto;

import hu.cubix.orderservice.patrik.model.Address;
import hu.cubix.orderservice.patrik.model.Status;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private long id;

    private Long shipmentId;

    private String username;

    private Status status;

    private Address address;

    private List<ProductWithInfoDto> productWithInfos;
}
