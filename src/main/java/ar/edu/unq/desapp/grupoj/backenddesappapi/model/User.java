package ar.edu.unq.desapp.grupoj.backenddesappapi.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userId","userNick"})}
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    //@Column(name="userSource")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="source_id")
    private Source source;


    @Column(name="userId")
    private String userId;


    @Column(name="userNick")
    private String userNick;


    @ManyToOne(cascade = CascadeType.ALL)
    protected Location location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    protected List<Review> reviews = new ArrayList<Review>();

    protected User(){}

    public User (Source source, String userId,String userNick,Location location){
        this.source=source;
        this.userId=userId;
        this.userNick=userNick;
        this.location = location;
    }


    public String getUserNick() {
        return userNick;
    }

    public Location getLocation() {
        return location;
    }

    public String getUserId() {
        return userId;
    }

    public void addReview(Review review){
        reviews.add(review);
    }
}
