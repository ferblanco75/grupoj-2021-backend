package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


public class ReviewRate {
    private Integer id;
    private Review review;

    public Long ratingUp ;
    public Long ratingDown;

    protected ReviewRate(){}

    public Long getRatingDown() {
        return ratingDown;
    }
    public Long getRatingUp() {
        return ratingUp;
    }
}
