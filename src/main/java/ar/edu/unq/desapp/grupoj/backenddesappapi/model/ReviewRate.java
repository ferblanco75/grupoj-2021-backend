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
