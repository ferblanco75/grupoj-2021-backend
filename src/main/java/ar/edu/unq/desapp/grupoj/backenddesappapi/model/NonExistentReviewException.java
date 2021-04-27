package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class NonExistentReviewException extends Exception {
    public NonExistentReviewException(Integer sourceId) {super("There is no review with ID " + sourceId.toString());}
}
