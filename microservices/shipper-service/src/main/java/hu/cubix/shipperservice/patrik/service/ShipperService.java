package hu.cubix.shipperservice.patrik.service;

import hu.cubix.shipperservice.patrik.model.Shipment;
import hu.cubix.webshop.patrik.ws.ShipmentMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ShipperService {

    private final JmsTemplate jmsTemplate;

    @Async
    public CompletableFuture<Long> addShipmentAsync(Shipment shipment) {
        System.out.println("ShipperService.addShipmentAsync called at thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }

        sendMessage(shipment.getId());

        return CompletableFuture.completedFuture(shipment.getId());
    }

    @Async
    public CompletableFuture<Long> getShipmentId() {
        System.out.println("ShipperService.getShipmentId called at thread " + Thread.currentThread().getName());

        Random random = new Random();
        return CompletableFuture.completedFuture(random.nextLong(0, 1800));
    }

    public void sendMessage(Long shipmentId) {
        Random random = new Random();
        ShipmentMessage payload = new ShipmentMessage();
        payload.setShipmentId(shipmentId);
        payload.setStatus(random.nextBoolean());

        jmsTemplate.convertAndSend("shippingstatus", payload);
    }
}
