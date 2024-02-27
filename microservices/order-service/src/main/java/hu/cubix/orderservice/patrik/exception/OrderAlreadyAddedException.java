package hu.cubix.orderservice.patrik.exception;

public class OrderAlreadyAddedException extends RuntimeException {

    private static final String MESSAGE = "Order already added!";

    public OrderAlreadyAddedException() {
        super(MESSAGE);
    }
}
