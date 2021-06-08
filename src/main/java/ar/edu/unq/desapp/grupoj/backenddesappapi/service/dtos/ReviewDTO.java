package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;


import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@Builder @AllArgsConstructor
@Getter
public class ReviewDTO {

    private Integer id;
    private Integer titleId;
    private String text;
    private String textExtended;
    private Integer rating;
    private Boolean spoilerAlert=false;
    private Date date=Date.from(Instant.now());
    private Integer languageId;
    private ReviewType type=ReviewType.NORMAL;

    private UserDTO user;

    //TODO Se deberia ir
    //public Language language;

    public ReviewDTO(Integer titleId,String text, String textExtended, Integer rating, Boolean spoilerAlert,Integer languageId, UserDTO user){
        this.titleId=titleId;
        this.text=text;
        this.textExtended=textExtended;
        this.rating=rating;
        this.spoilerAlert=spoilerAlert;
        this.languageId=languageId;
        this.user=user;
    }

    public ReviewDTO(Integer id, Integer titleId, String text, String textExtended, Integer rating, Boolean spoilerAlert, Integer languageId, UserDTO user){
        this.id=id;
        this.titleId=titleId;
        this.text=text;
        this.textExtended=textExtended;
        this.rating=rating;
        this.spoilerAlert=spoilerAlert;
        this.languageId=languageId;
        this.user=user;
    }

    protected ReviewDTO(){}


    public static ReviewDTO fromModel(Review review){
        return new ReviewDTO(review.getId(),
                review.getTitleId(),
                review.getText(),
                review.getTextExtended(),
                review.getRating(),
                review.getSpoilerAlert(),
                review.getLanguage().getId(),
                UserDTO.fromModel((User) review.getUser()));
    }

    public Review toModel(Language language, Critic user) {
        return new Review(titleId,user,text,textExtended,rating,spoilerAlert,language);
    }

    public Integer getId() {
        return id;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public ReviewType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getSpoilerAlert() {
        return spoilerAlert;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getTextExtended() {
        return textExtended;
    }

    public UserDTO getUser() {
        return user;
    }

}
