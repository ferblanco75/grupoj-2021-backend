package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import jdk.jfr.Name;

import javax.persistence.*;

@Entity
public class ReviewReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("idReviewReport")
    private Integer id;

    private Reason reason;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Review review;


    protected ReviewReport(){}
    public ReviewReport( Reason aReason, User aUser){
        this.reason=aReason;
        this.user=aUser;
    }

    public Integer getId() {
        return id;
    }

    public Reason getReason() {
        return reason;
    }

    public User getUser() {
        return user;
    }

}
