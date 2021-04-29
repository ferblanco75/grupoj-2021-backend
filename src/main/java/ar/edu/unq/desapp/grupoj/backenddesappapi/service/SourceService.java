package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class SourceService {

    private final SourceRepository sourceRepo;

    @Autowired
    public SourceService(SourceRepository aRepository) {
        this.sourceRepo = aRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        sourceRepo.save(new Source("Netflix"));
        sourceRepo.save(new Source("Disney+"));
        sourceRepo.save(new Source("Amazon Prime Video"));
        sourceRepo.save(new Source("Paramount"));
        sourceRepo.save(new Source("HBO Go"));

    }

    @GetMapping("/sources")
    public Iterable<Source> getAll() {
        return sourceRepo.findAll();
    }

    @GetMapping("/sources/{id}")
    public Source getById(@PathVariable(value = "id") Integer id) throws NonExistentSourceException{
        return sourceRepo.getById(id).orElseThrow(() -> new NonExistentSourceException(id));
    }
}
