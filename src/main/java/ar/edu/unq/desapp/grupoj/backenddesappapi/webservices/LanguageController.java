package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class LanguageController {

    @Autowired
    private LanguageService service;

    @GetMapping("/language")
    public Iterable<Language> getAll() {
        return service.findAll();
    }


}
