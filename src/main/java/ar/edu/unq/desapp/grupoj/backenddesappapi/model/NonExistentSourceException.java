package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class NonExistentSourceException extends Exception {
    public NonExistentSourceException(Integer sourceId) {super("There is no source with ID " + sourceId.toString());}
}
