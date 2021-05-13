package ar.edu.unq.desapp.grupoj.backenddesappapi.exception;

public class NonExistentTitleException extends Exception {
    public NonExistentTitleException(Integer titleId) {
        super("There is no Title with ID " + titleId.toString());
    }
}
