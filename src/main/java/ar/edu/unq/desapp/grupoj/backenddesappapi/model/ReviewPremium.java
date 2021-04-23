package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.*;


@Entity
public class ReviewPremium extends Review{

    public ReviewPremium(Integer movieId, Source source, String text, String textExtended, Integer rating, Boolean haveSpoiler,String userId,String userNick,Location location, Language language){
        super(movieId,source,text,textExtended,rating,haveSpoiler,userId,userNick,location,language);
        super.type= ReviewType.PREMIUM;
    }

    protected ReviewPremium(){ }

}
