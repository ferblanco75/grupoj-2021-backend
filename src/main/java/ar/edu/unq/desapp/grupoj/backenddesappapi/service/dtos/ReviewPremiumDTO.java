package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;

import java.time.Instant;
import java.util.Date;


public class ReviewPremiumDTO {

    public Integer titleId;
    public String text;
    public String textExtended;
    public Integer rating;
    public Boolean spoilerAlert=false;
    public Date date=Date.from(Instant.now());

    public Integer languageId;
    public ReviewType type=ReviewType.PREMIUM;

    public CriticDTO critic;

    public ReviewPremiumDTO() {
    }




    public ReviewPremium toModel(Language language, Critic critic){
        return new ReviewPremium(titleId, critic, text, textExtended, rating, spoilerAlert,language);
    }

}
