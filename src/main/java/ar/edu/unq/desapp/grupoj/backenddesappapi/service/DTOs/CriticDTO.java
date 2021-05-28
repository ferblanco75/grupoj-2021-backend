package ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs;

public class CriticDTO {
    public Integer sourceId;
    public String userId;


    protected CriticDTO(){}

    public CriticDTO(Integer sourceId,String userId){
        this.sourceId=sourceId;
        this.userId=userId;
    }



}
