package hu.cubix.catalogservice.patrik.exception;

public class CategoryAlreadyAddedException extends RuntimeException {

    private static final String MESSAGE = "Category already added!";

    public CategoryAlreadyAddedException() {
        super(MESSAGE);
    }
}
