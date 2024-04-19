package by.vitikova.discovery.exception;

public class SaveImageException extends RuntimeException {

    public SaveImageException(String message) {
        super(message);
    }

    public SaveImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
