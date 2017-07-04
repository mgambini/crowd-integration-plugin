package it.ditech.go.authorization.crowd.executor;

public class NoSuchRequestHandler extends RuntimeException {
    public NoSuchRequestHandler(String message) {
        super(message);
    }
}
