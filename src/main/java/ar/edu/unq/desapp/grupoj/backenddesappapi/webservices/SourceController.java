package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @GetMapping("/sources")
    public Iterable<Source> getAll() {
        return sourceService.findAll();
    }

    @GetMapping("/sources/{id}")
    public Source getById(@PathVariable(value = "id") Integer id) throws NonExistentSourceException {
        return sourceService.getById(id).orElseThrow(() -> new NonExistentSourceException(id));
    }

}
