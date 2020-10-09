package uk.kainos.seleniumframework.driver;

public class DriverCreationException extends RuntimeException {

    public DriverCreationException(String message) {
        super(message);
    }

    public DriverCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
