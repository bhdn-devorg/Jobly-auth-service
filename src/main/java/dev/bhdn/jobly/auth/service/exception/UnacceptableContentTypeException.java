package dev.bhdn.jobly.auth.service.exception;

public class UnacceptableContentTypeException extends RuntimeException {
    public UnacceptableContentTypeException(String message) {
        super(message);
    }
}
