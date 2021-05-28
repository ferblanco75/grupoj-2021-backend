package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewType;
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

     public Review toModel(Language language, User user) {
        return new Review(titleId, user, text, textExtended, rating, spoilerAlert,language);
    }

}
