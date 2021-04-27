package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReviewRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ratingUp = 0;
    private Integer ratingDown = 0;

    protected ReviewRate(){}


    public void rateUp(){this.ratingUp++;}
    public void rateDown(){this.ratingDown++;}

    public Integer getRatingDown() {
        return ratingDown;
    }

    public Integer getRatingUp() {
        return ratingUp;
    }
}
