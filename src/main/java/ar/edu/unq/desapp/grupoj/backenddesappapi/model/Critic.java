package ar.edu.unq.desapp.grupoj.backenddesappapi.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "critic",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId"})}
)
public class Critic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="userId")
    private String userId;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    protected List<Review> reviews = new ArrayList<Review>();

    protected Critic(){}

    public Critic(String userId){
        this.userId=userId;
    }


    public String getUserId() {
        return userId;
    }

    public void addReview(Review review){
        reviews.add(review);
    }
}
