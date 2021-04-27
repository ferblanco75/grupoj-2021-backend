package ar.edu.unq.desapp.grupoj.backenddesappapi.model;



import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LocationRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;

import java.time.Instant;
import java.util.Date;


public class ReviewAdapter {

    public Integer idMovie;
    public String text;
    public String textExtended;
    public Integer rating;
    public Boolean spoilerAlert=false;
    public Date date=Date.from(Instant.now());
    public String userId;
    public String userNick;

    public Integer locationId;

    public Integer sourceId;

    public Integer languageId;

    public ReviewType type=ReviewType.NORMAL;

    public ReviewAdapter() {
    }


    public Review toModel(SourceRepository sourceRepository, LocationRepository locationRepository, LanguageRepository languageRepository) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        Source source= sourceRepository.getById(sourceId).orElseThrow(() -> new NonExistentSourceException(sourceId));
        Location location= locationRepository.getById(locationId).orElseThrow(() -> new NonExistentLocationException(locationId));
        Language language= languageRepository.getById(languageId).orElseThrow(() -> new NonExistentLanguageException(languageId));
        return new Review(idMovie, source, text, textExtended, rating, spoilerAlert, userId, userNick,location,language);

    }

}
