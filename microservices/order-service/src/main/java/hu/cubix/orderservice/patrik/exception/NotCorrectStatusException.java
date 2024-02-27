package hu.cubix.orderservice.patrik.exception;

public class NotCorrectStatusException extends RuntimeException {

    private static final String MESSAGE = "Not correct status!";

    public NotCorrectStatusException(String msg) {
        super(MESSAGE.concat(" ").concat(msg));
    }
}
