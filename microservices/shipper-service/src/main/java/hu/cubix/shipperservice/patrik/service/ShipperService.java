package hu.cubix.shipperservice.patrik.service;

import hu.cubix.shipperservice.patrik.model.Shipment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class ShipperService {

    private Random random = new Random();

    @Async
    public CompletableFuture<Long> addShipmentAsync(Shipment shipment) {
        System.out.println("ShipperService.addShipmentAsync called at thread " + Thread.currentThread().getName());

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        }
        return CompletableFuture.completedFuture(shipment.getId());
    }

    @Async
    public CompletableFuture<Long> getShipmentId() {
        System.out.println("ShipperService.getShipmentId called at thread " + Thread.currentThread().getName());

        return CompletableFuture.completedFuture(random.nextLong(0, 1800));
    }
}
