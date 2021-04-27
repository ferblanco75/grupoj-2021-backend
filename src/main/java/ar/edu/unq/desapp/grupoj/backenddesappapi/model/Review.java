package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idMovie;
    private String text;
    private String textExtended;
    private Integer rating;
    private Boolean spoilerAlert=false;
    private Date date=Date.from(Instant.now());
    private String userId;
    private String userNick;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Location location;

    @ManyToOne(cascade = {CascadeType.ALL})
    protected Source source;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Language language;

    protected ReviewType type=ReviewType.NORMAL;

    @OneToOne(cascade = CascadeType.ALL)
    protected ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewRate ReviewRate = new ReviewRate();

    protected Review() {
    }

    public Review(Integer movieId, Source source, String text, String textExtended, Integer rating, Boolean haveSpoiler,String userId,String userNick,Location location, Language lang){
        this.text=text;
        this.rating = rating;
        this.textExtended= textExtended;
        this.idMovie=movieId;
        this.spoilerAlert=haveSpoiler;
        this.userId=userId;
        this.userNick=userNick;
        this.source=source;
        this.location=location;
        this.language=lang;

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

    public String getUserId() {
        return userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public Integer getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public Location getLocation() {
        return location;
    }

    public ReviewType getType() {
        return type;
    }

    public Language getLanguage() {
        return language;
    }

    public ReviewRate getReviewRate() {
        return ReviewRate;
    }
}
