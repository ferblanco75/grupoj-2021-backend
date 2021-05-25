package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;

import java.time.Instant;
import java.util.Date;


public class ReviewDTO {

    public Integer titleId;
    public String text;
    public String textExtended;
    public Integer rating;
    public Boolean spoilerAlert=false;
    public Date date=Date.from(Instant.now());

    public Integer languageId;
    public ReviewType type=ReviewType.NORMAL;

    public UserDTO user;

    public ReviewDTO() {
    }


    public Review toModel(Language language, User user) {
        return new Review(titleId, user, text, textExtended, rating, spoilerAlert,language);
    }

}
