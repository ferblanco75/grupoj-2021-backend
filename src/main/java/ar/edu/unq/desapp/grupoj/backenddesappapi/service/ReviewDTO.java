package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;

import java.time.Instant;
import java.util.Date;

import lombok.*;

@Builder @AllArgsConstructor
@Getter
public class ReviewDTO {

    public Integer titleId;
    public String text;
    public String textExtended;
    public Integer rating;
    public Boolean spoilerAlert=false;
    public Date date=Date.from(Instant.now());

    public Integer languageId;
    public ReviewType type=ReviewType.NORMAL;
    public Language language;

    public UserDTO user;

    public Date getDate() {
        return date;
    }

    public ReviewDTO() {
    }



    public ReviewDTO(Integer titleId,  String text, String textExtended, Integer rating, boolean spoilerAlert, Language language ) {
        this.titleId = titleId;

        this.text = text;
        this.textExtended = textExtended;
        this.rating = rating;
        this.spoilerAlert = spoilerAlert;
        this.language = language;
    }


    public Review toModel(Language language, User user) {
        return new Review(titleId, user, text, textExtended, rating, spoilerAlert,language);
    }

    public Integer getTitleId() {
        return titleId;
    }
}
