package hu.cubix.orderservice.patrik.service;

import hu.cubix.orderservice.patrik.exception.AddressNotFoundException;
import hu.cubix.orderservice.patrik.exception.NotCorrectStatusException;
import hu.cubix.orderservice.patrik.exception.OrderAlreadyAddedException;
import hu.cubix.orderservice.patrik.exception.OrderNotFoundException;
import hu.cubix.orderservice.patrik.model.Address;
import hu.cubix.orderservice.patrik.model.OrderModel;
import hu.cubix.orderservice.patrik.model.OrderedProduct;
import hu.cubix.orderservice.patrik.model.Status;
import hu.cubix.orderservice.patrik.repository.AddressRepository;
import hu.cubix.orderservice.patrik.repository.OrderModelRepository;
import hu.cubix.orderservice.patrik.repository.OrderedProductRepository;
import hu.cubix.orderservice.patrik.repository.ProductWithInfoRepository;
import hu.cubix.shipperservice.patrik.wsclient.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderModelRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderedProductRepository productRepository;
    private final ProductWithInfoRepository infoRepository;

    @Override
    @Transactional
    public OrderModel create(OrderModel order) {
        if (orderRepository.existsById(order.getId())) {
            throw new OrderAlreadyAddedException();
        }
        checkAddress(order.getAddress());
        orderRepository.save(order);

        order.getProductWithInfos().forEach(info -> {
            productRepository.save(info.getProduct());
            info.setOrder(order);
            infoRepository.save(info);

        });
        order.setStatus(Status.PENDING);

        return order;
    }

    @Transactional
    private void checkAddress(Address address) {
        if (address.getId() != 0
                && !addressRepository.existsById(address.getId())) {
            throw new AddressNotFoundException();
        }
        if (address.getId() == 0) {
            addressRepository.save(address);
        }
    }

    @Override
    @Transactional
    public List<OrderModel> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public OrderModel handleOrder(long id, Status status) {
        OrderModel order = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        if (!status.equals(Status.CONFIRMED) && !status.equals(Status.DECLINED)) {
            throw new NotCorrectStatusException(
                    "Status can be: "
                    .concat(Status.CONFIRMED.toString())
                    .concat(", or ")
                    .concat(Status.DECLINED.toString()));
        }
        order.setStatus(status);

        if (status.equals(Status.CONFIRMED)) {
            System.out.println("ShipmentId: " + callXmlWs(order));
        }

        return orderRepository.save(order);
    }

    public Long callXmlWs(OrderModel order) {
        Shipment shipment = new Shipment();
        order.getProductWithInfos().forEach(info -> {
            ShippableProduct shippableProduct = new ShippableProduct();
            shippableProduct.setName(info.getProduct().getName());
            shippableProduct.setCount(info.getCount());
            shipment.getProducts().add(shippableProduct);
        });

        hu.cubix.shipperservice.patrik.wsclient.Address fromAddress = new hu.cubix.shipperservice.patrik.wsclient.Address();
        fromAddress.setCountry("HU");
        fromAddress.setZip("3300");
        fromAddress.setCity("Eger");
        fromAddress.setStreet("Test u 2");

        hu.cubix.shipperservice.patrik.wsclient.Address toAddress = new hu.cubix.shipperservice.patrik.wsclient.Address();
        Address orderAddress = order.getAddress();
        toAddress.setCountry(orderAddress.getCountry());
        toAddress.setZip(orderAddress.getZip());
        toAddress.setCity(orderAddress.getCity());
        toAddress.setStreet(orderAddress.getStreet());

        shipment.setFromAddress(fromAddress);
        shipment.setToAddress(toAddress);

        ShipperXmlWs shipperXmlWsImplPort = new ShipperXmlWsImplService().getShipperXmlWsImplPort();
        AddShipment addShipment = new AddShipment();
        addShipment.setArg0(shipment);
        return shipperXmlWsImplPort.addShipment(addShipment);
    }
}
