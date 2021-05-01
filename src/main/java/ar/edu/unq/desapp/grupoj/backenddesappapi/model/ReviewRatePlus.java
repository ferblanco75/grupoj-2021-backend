package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.*;

@Entity
public class ReviewRatePlus  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private RateType type;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;


    public ReviewRatePlus(RateType type, User user){
        this.type=type;
        this.user=user;

    }
    protected ReviewRatePlus(){}

    public RateType getType() {
        return type;
    }
}
