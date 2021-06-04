package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.TitleType;


public class TitleDTO {
    private Integer titleId;
    private String title;
    private Boolean isAdult;
    private Integer duration;
    private TitleType type;
    private Integer startYear;
    private Integer endYear;


    public TitleDTO(Integer id,String title, Integer time, TitleType type, Integer startYear, Integer endYear,Boolean forAdult){
        this.title=title;
        this.titleId=id;
        this.duration=time;
        this.type=type;
        this.startYear=startYear;
        this.endYear = endYear;
        this.isAdult=forAdult;
    }


    public static TitleDTO fromModel(Title title){
        return new TitleDTO(title.getTitleId(),
                title.getTitle(),
                title.getDuration(),
                title.getTitleType(),
                title.getStartYear(),
                title.getEndYear(),
                title.getAdult());
    }
    protected TitleDTO(){}

    public Integer getTitleId() {
        return titleId;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public TitleType getType() {
        return type;
    }

}
