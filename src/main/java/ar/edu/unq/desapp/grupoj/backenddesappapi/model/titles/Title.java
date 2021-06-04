package ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.cast.Cast;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.cast.Job;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.cast.Person;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="titles")

public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer titleId;
    private TitleType titleType;
    private String title;
    private Boolean isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer duration;


    @ElementCollection(fetch = FetchType.EAGER, targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "generos_enum")
    @Column(name = "generos")
    private List<Genre> genres;

    @OneToMany
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cast> cast= new ArrayList<Cast>();


    public Title(Integer id,TitleType type, String title, Boolean isAdult, Integer startYear,Integer endYear,Integer duration, List<Genre> genres){
        this.titleId=id;
        this.titleType=type;
        this.title=title;
        this.isAdult=isAdult;
        this.startYear=startYear;
        this.endYear=endYear;
        this.duration=duration;
        this.genres=genres;

    }

    public Title(){}

    public void addReview(Review review){
        reviews.add(review);
    }
    public void addCast(Person person, Job job){
        cast.add(new Cast(person,job));
    }


    public Integer getTitleId() {
        return titleId;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public String getTitle() {
        return title;
    }

    public TitleType getTitleType() {
        return titleType;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Integer getStartYear() {
        return startYear;
    }



}
