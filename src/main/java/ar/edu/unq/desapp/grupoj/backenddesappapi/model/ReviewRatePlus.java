package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table (name = "review_rates",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","review_id"})}
)

public class ReviewRatePlus  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("idReviewRate")

    private Integer id;

    private RateType type;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Review review;


    public ReviewRatePlus(RateType type, User user,Review review){
        this.type=type;
        this.user=user;
        this.review=review;

    }
    protected ReviewRatePlus(){}

    public RateType getType() {
        return type;
    }
}
