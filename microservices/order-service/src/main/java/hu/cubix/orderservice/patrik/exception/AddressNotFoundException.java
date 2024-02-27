package hu.cubix.orderservice.patrik.exception;

public class AddressNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Address not found!";

    public AddressNotFoundException() {
        super(MESSAGE);
    }
}
