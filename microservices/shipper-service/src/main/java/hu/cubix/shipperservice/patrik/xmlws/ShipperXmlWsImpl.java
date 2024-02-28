package hu.cubix.shipperservice.patrik.xmlws;

import hu.cubix.shipperservice.patrik.model.Shipment;
import hu.cubix.shipperservice.patrik.service.ShipperService;
import jakarta.xml.ws.AsyncHandler;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.annotations.UseAsyncMethod;
import org.apache.cxf.jaxws.ServerAsyncResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class ShipperXmlWsImpl implements ShipperXmlWs {

    private final ShipperService shipperService;

    @Override
    @UseAsyncMethod
    public Long addShipment(Shipment shipment) {
        return 0L;
    }

    public Future<Long> addShipmentAsync(Shipment shipment, AsyncHandler<Long> asyncHandler) {
        ServerAsyncResponse<Long> serverAsyncResponse = new ServerAsyncResponse<>();
        System.out.println(Thread.currentThread().getName());

        shipperService.getShipmentId().thenAccept(result ->{
            System.out.println(Thread.currentThread().getName());
            serverAsyncResponse.set(result);
            asyncHandler.handleResponse(serverAsyncResponse);
            shipment.setId(result);
        });

        shipperService.addShipmentAsync(shipment).thenAccept(result ->{
            System.out.println(Thread.currentThread().getName());
            serverAsyncResponse.set(result);
            asyncHandler.handleResponse(serverAsyncResponse);
            System.out.println("Shipment added with id: " + result);
        });

        return serverAsyncResponse;
    }
}
