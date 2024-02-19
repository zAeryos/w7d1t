package it.epicode.w7d1t.exceptions;

public class FailedLoginException extends RuntimeException {

    public FailedLoginException(String message) {
        super(message);
    }

}
