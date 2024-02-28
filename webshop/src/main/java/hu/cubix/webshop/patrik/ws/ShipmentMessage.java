package hu.cubix.webshop.patrik.ws;

import lombok.Data;

@Data
public class ShipmentMessage {

    private Long shipmentId;
    private Boolean status;
}
