package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewPremium;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;

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




    public ReviewPremium toModel(LanguageRepository languageRepository) throws NonExistentSourceException {
        Language language= languageRepository.getById(languageId).orElseThrow(() -> new NonExistentSourceException(languageId));
        return new ReviewPremium(titleId, text, textExtended, rating, spoilerAlert,language);
    }

}
