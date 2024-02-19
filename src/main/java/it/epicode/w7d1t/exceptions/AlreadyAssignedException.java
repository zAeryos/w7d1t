package it.epicode.w7d1t.exceptions;

public class AlreadyAssignedException extends RuntimeException {
    public AlreadyAssignedException(String message) {
        super(message);
    }
}
