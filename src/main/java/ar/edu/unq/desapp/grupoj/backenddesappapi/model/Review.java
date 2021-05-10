package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import jdk.jfr.Name;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "reviews",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"titleId","user_id"})}
)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer titleId;

    private String text;
    private String textExtended;
    private Integer rating;
    private Boolean spoilerAlert=false;
    private Date date=Date.from(Instant.now());

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    protected User user;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Language language;


    protected ReviewType type=ReviewType.NORMAL;

    @OneToMany(targetEntity= ReviewRate.class, cascade = CascadeType.ALL)
    @Name("reviewRates")
    protected List<ReviewRate> reviewRates = new ArrayList<>();

    protected Review() {
    }

    public Review(Integer titleId, String text, String textExtended, Integer rating, Boolean haveSpoiler, Language language){
        this.text=text;
        this.rating = rating;
        this.textExtended= textExtended;
        this.titleId=titleId;
        this.spoilerAlert=haveSpoiler;

        this.language=language;

    }

    public Integer getRating() {
        return rating;
    }
    public String getText() {
        return text;
    }
    public String getTextExtended() {return textExtended;}

    public Integer getTitleId() {
        return titleId;
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


    public ReviewType getType() {
        return type;
    }

    public Rates getReviewRate() {
        Rates rate = new Rates();
        rate.ratingUp= reviewRates.stream().filter(i -> i.getType()==RateType.UP).count();
        rate.ratingDown= reviewRates.stream().filter(i -> i.getType()==RateType.DOWN).count();
        return rate;
    }

    public Language getLanguage() {
        return language;
    }

    public void addRate(ReviewRate reviewRate){
        reviewRates.add(reviewRate);
    }
}
