package ar.edu.unq.desapp.grupoj.backenddesappapi.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "critic",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"sourceId","critic_id"})}
)
public class Critic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    @Column(name="sourceId")
    private Integer sourceId;

    @Column(name="critic_id")
    private String userId;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="critic_id")
    protected List<Review> reviews = new ArrayList<Review>();

    protected Critic(){}

    public Critic(Integer sourceId, String userId){
        this.sourceId=sourceId;
        this.userId=userId;
    }


    public String getUserId() {
        return userId;
    }

    public void addReview(Review review){
        reviews.add(review);
    }
}
