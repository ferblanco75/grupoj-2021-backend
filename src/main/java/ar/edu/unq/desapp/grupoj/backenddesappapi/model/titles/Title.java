package ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles;

import javax.persistence.*;
import java.util.Date;
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
    private Date startYear;
    private Date endYear;
    private Integer duration;


    @ElementCollection(fetch = FetchType.EAGER, targetClass = Genre.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "generos_enum")
    @Column(name = "generos")
    private List<Genre> genres;

    public Title(Integer id,TitleType type, String title, Boolean isAdult, Date startYear,Date endYear,Integer duration, List<Genre> genres){
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

    public Date getEndYear() {
        return endYear;
    }

    public Date getStartYear() {
        return startYear;
    }
}
