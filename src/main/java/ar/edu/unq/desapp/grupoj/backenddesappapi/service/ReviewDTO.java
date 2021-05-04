package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentLanguageException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;

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


    public Review toModel(LanguageRepository languageRepository) throws NonExistentLanguageException {
        Language language= languageRepository.getById(languageId).orElseThrow(() -> new NonExistentLanguageException(languageId));
        return new Review(titleId, text, textExtended, rating, spoilerAlert,language);
    }

}
