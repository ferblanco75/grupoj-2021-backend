package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Critic;

public class CriticDTO {
    public Integer sourceId;
    public String userId;


    protected CriticDTO(){}

    public CriticDTO(Integer sourceId,String userId){
        this.sourceId=sourceId;
        this.userId=userId;
    }

    public Critic toModel()  {
        return new Critic(sourceId,userId);
    }

}
