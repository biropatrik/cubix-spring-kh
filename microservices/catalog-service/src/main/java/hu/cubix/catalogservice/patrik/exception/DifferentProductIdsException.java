package hu.cubix.catalogservice.patrik.exception;

public class DifferentProductIdsException extends RuntimeException {

    private static final String MESSAGE = "The path id and the body id must match!";

    public DifferentProductIdsException() {
        super(MESSAGE);
    }
}
