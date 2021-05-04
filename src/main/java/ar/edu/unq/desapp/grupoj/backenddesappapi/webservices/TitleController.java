package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/title")
    public Iterable<Title> getAll() {
        return titleService.findAll();
    }

    @GetMapping("/title/{id}")
    public Title getById(@PathVariable(value = "id") Integer id) throws NonExistentSourceException {
        return titleService.getByTitleId(id).orElseThrow(() -> new NonExistentSourceException(id));
    }

}
