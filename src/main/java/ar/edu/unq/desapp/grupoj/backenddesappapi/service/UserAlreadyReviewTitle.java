package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

public class UserAlreadyReviewTitle extends Exception {
    public UserAlreadyReviewTitle(Integer titleId, String uniqueIdString) {
        super("User already review titleId" + titleId + "("+uniqueIdString+")");
    }
}
