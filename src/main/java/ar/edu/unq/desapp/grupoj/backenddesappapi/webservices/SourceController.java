package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.Aspect.LogActivity;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SourceService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.SourceDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentSourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @LogActivity
    @GetMapping("/sources")
    public List<SourceDTO> getAll() {
        return sourceService.findAll()
                .stream()
                .map(i->SourceDTO.fromModel(i))
                .collect(Collectors.toList());
    }

    @GetMapping("/sources/{id}")
    public SourceDTO getById(@PathVariable(value = "id") Integer id) throws NonExistentSourceException {
        return SourceDTO.fromModel(sourceService.getById(id));
    }

}
