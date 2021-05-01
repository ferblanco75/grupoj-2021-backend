package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.*;

@Entity
public class ReviewRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idReview")
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "idReview")
    private Review review;

    public Long ratingUp ;
    public Long ratingDown;

    protected ReviewRate(){}


    public void rateUp(){this.ratingUp++;}
    public void rateDown(){this.ratingDown++;}

    public Long getRatingDown() {
        return ratingDown;
    }

    public Long getRatingUp() {
        return ratingUp;
    }
}
