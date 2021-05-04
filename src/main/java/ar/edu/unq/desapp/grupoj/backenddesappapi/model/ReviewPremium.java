package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.*;


@Entity
public class ReviewPremium extends Review{

    public ReviewPremium(Integer titleId, String text, String textExtended, Integer rating, Boolean haveSpoiler, Language language){
        super(titleId,text,textExtended,rating,haveSpoiler, language);
        super.type= ReviewType.PREMIUM;
    }

    protected ReviewPremium(){ }

}
