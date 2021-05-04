package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class LanguageService {

    private final LanguageRepository repository;

    @Autowired
    public LanguageService(LanguageRepository aRepository) {
        this.repository= aRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        repository.save(new Language("Español"));
        repository.save(new Language("Ingles"));
        repository.save(new Language("Portugues"));
        repository.save(new Language("Frances"));
    }

    public Iterable<Language> findAll() {
        return repository.findAll();
    }
}
