package ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Genre;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.TitleType;


public class TitleDTO {


    public Integer titleId;
    public String title;
    private Boolean isAdult;

    public Integer duration;
    public TitleType type;
    public Integer startYear;
    public Integer endYear;


    public TitleDTO(Integer id,String title, Integer time, TitleType type, Integer startYear, Integer endYear,Boolean forAdult){
        this.title=title;
        this.titleId=id;
        this.duration=time;
        this.type=type;
        this.startYear=startYear;
        this.endYear = endYear;
        this.isAdult=forAdult;
    }

    protected TitleDTO(){}







}
