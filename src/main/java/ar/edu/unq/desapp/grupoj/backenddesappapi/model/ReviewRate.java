package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table (name = "review_rates",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","review_id"})}
)

public class ReviewRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("idReviewRate")

    private Integer id;

    private RateType type;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Review review;


    public ReviewRate(RateType type, User user, Review review){
        this.type=type;
        this.user=user;
        this.review=review;

    }
    protected ReviewRate(){}

    public RateType getType() {
        return type;
    }
}
