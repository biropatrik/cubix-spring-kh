package hu.cubix.shipperservice.patrik.xmlws;

import hu.cubix.shipperservice.patrik.model.Shipment;
import jakarta.jws.WebService;
import jakarta.xml.ws.ResponseWrapper;

@WebService
public interface ShipperXmlWs {

    @ResponseWrapper(localName = "addShipmentResponse", className = "java.lang.Long")
    public Long addShipment(Shipment shipment);
}
