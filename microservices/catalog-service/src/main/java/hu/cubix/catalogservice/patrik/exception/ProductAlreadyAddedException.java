package hu.cubix.catalogservice.patrik.exception;

public class ProductAlreadyAddedException extends RuntimeException {

    private static final String MESSAGE = "Product already added!";

    public ProductAlreadyAddedException() {
        super(MESSAGE);
    }
}
