package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


public class Rates {
    private Integer id;
    private Review review;

    public Long ratingUp ;
    public Long ratingDown;

    protected Rates(){}

    public Long getRatingDown() {
        return ratingDown;
    }
    public Long getRatingUp() {
        return ratingUp;
    }
}
