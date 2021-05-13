package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

public class CriticDTO {
    public Integer sourceId;
    public String userId;


    protected CriticDTO(){}

    public CriticDTO(Integer sourceId,String userId){
        this.sourceId=sourceId;
        this.userId=userId;
    }



}
