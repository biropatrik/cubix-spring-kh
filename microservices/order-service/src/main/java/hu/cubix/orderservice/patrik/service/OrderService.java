package hu.cubix.orderservice.patrik.service;

import hu.cubix.orderservice.patrik.model.OrderModel;
import hu.cubix.orderservice.patrik.model.Status;

import java.util.List;

public interface OrderService {

    public OrderModel create(OrderModel order);

    public List<OrderModel> findByUsername(String username);

    public OrderModel handleOrder(long id, Status status);
}
