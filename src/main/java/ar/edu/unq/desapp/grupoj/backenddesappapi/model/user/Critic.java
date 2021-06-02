package ar.edu.unq.desapp.grupoj.backenddesappapi.model.user;



import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "critic",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"source","critic_id"})}
)
public class Critic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name="source")
    private Source source;

    @Column(name="critic_id")
    private String userId;

    protected Critic(){}

    public Critic(Source source, String userId){
        this.source=source;
        this.userId=userId;
    }

    public Source getSource() {
        return source;
    }

    public String getUserId() {
        return userId;
    }

    public String getUniqueIdString(){
        return this.getSource().getName() + " " + getUserId();
    }

    public Integer getSourceId() {
        return this.source.getId();
    }
}
