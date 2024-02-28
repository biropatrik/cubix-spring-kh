package hu.cubix.orderservice.patrik.jms;

import hu.cubix.orderservice.patrik.service.OrderService;
import hu.cubix.webshop.patrik.ws.ShipmentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

	private final OrderService orderService;

	@JmsListener(destination = "shippingstatus")
	public void onShipmentMessage(ShipmentMessage shipmentMessage) {
		orderService.setShipmentStatus(shipmentMessage.getShipmentId(), shipmentMessage.getStatus());
	}
	
}
