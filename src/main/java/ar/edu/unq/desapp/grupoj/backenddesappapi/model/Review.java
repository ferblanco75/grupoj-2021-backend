package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(
        name = "review",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"idMovie","user_id"})}
)
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "idMovie")
    private Integer idMovie;

    private String text;
    private String textExtended;
    private Integer rating;
    private Boolean spoilerAlert=false;
    private Date date=Date.from(Instant.now());

    @ManyToOne(fetch = FetchType.LAZY)
        protected User user;


    @ManyToOne(cascade = {CascadeType.ALL})
    protected Source source;

    protected ReviewType type=ReviewType.NORMAL;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    protected ReviewRate reviewRate = new ReviewRate();

    protected Review() {
    }

    public Review(Integer movieId, Source source, String text, String textExtended, Integer rating, Boolean haveSpoiler){
        this.text=text;
        this.rating = rating;
        this.textExtended= textExtended;
        this.idMovie=movieId;
        this.spoilerAlert=haveSpoiler;
        this.source=source;

    }

    public Integer getRating() {
        return rating;
    }
    public String getText() {
        return text;
    }
    public String getTextExtended() {return textExtended;}

    public Integer getIdMovie() {
        return idMovie;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public Date getDate() {
        return date;
    }


    public Integer getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public ReviewType getType() {
        return type;
    }

    public ReviewRate getReviewRate() {
        return reviewRate;
    }


}
