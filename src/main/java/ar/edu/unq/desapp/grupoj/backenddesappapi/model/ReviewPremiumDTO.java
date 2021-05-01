package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;

import java.time.Instant;
import java.util.Date;


public class ReviewPremiumDTO {

    public Integer idMovie;
    public String text;
    public String textExtended;
    public Integer rating;
    public Boolean spoilerAlert=false;
    public Date date=Date.from(Instant.now());
    public Integer sourceId;
    public Integer languageId;
    public ReviewType type=ReviewType.PREMIUM;

    public String userId;

    public ReviewPremiumDTO() {
    }




    public ReviewPremium toModel(SourceRepository sourceRepository, LanguageRepository languageRepository) throws NonExistentSourceException{
        Source source= sourceRepository.getById(sourceId).orElseThrow(() -> new NonExistentSourceException(sourceId));
        Language language= languageRepository.getById(languageId).orElseThrow(() -> new NonExistentSourceException(languageId));
        return new ReviewPremium(idMovie, source, text, textExtended, rating, spoilerAlert,language);
    }

}
