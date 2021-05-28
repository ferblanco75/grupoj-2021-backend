package ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions;

public class NonExistentReviewException extends Exception {
    public NonExistentReviewException(Integer reviewId) {
        super("There is no review with ID " + reviewId.toString());
    }
}
