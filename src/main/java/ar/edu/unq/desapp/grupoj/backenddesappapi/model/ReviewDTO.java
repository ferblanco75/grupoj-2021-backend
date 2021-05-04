package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.ReviewRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.TitleRepository;

import java.time.Instant;
import java.util.Date;


public class ReviewDTO {

    public Integer idTitle;
    public String text;
    public String textExtended;
    public Integer rating;
    public Boolean spoilerAlert=false;
    public Date date=Date.from(Instant.now());
    public Integer sourceId;
    public Integer languageId;
    public ReviewType type=ReviewType.NORMAL;

    public String userId;
    public String userNick;
    public Integer locationId;

    public ReviewDTO() {
    }


    public Review toModel(SourceRepository sourceRepository, LanguageRepository languageRepository) throws NonExistentSourceException{
        Source source= sourceRepository.getById(sourceId).orElseThrow(() -> new NonExistentSourceException(sourceId));
        Language language= languageRepository.getById(languageId).orElseThrow(() -> new NonExistentSourceException(languageId));
        return new Review(idTitle, source, text, textExtended, rating, spoilerAlert,language);
    }

}
