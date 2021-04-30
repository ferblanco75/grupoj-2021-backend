package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.*;


@Entity
public class ReviewPremium extends Review{

    public ReviewPremium(Integer movieId, Source source, String text, String textExtended, Integer rating, Boolean haveSpoiler,User user){
        super(movieId,source,text,textExtended,rating,haveSpoiler);
        super.type= ReviewType.PREMIUM;
    }

    protected ReviewPremium(){ }

}
