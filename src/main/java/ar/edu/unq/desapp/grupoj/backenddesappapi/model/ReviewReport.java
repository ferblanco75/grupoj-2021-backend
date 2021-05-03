package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class ReviewReport {

    private Review review;
    private Reason reason;
    private User user;

    public ReviewReport(Review aReview, Reason aReason, User aUser){
        this.reason=aReason;
        this.user=aUser;
        this.review=aReview;
    }

}
