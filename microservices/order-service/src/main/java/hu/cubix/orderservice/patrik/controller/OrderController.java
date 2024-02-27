package hu.cubix.orderservice.patrik.controller;

import hu.cubix.orderservice.patrik.dto.OrderDto;
import hu.cubix.orderservice.patrik.mapper.OrderMapper;
import hu.cubix.orderservice.patrik.model.Status;
import hu.cubix.orderservice.patrik.service.OrderService;
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Stack;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody @Valid OrderDto orderDto) {
        return ResponseEntity.ok(
                orderMapper.orderToDto(
                        orderService.create(
                                orderMapper.dtoToOrder(orderDto))));
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<OrderDto>> getOrdersByUsername(@PathVariable String username) {
        return ResponseEntity.ok(
                orderMapper.ordersToDtos(
                        orderService.findByUsername(username)));
    }

    @PutMapping("/handle/{id}")
    public ResponseEntity<OrderDto> handleOrder(@PathVariable long id, @RequestParam Status status) {
        return ResponseEntity.ok(
                orderMapper.orderToDto(
                        orderService.handleOrder(id, status)));
    }
}
