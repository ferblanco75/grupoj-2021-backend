package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.SourceDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
public class SourceController {

    @Autowired
    private SourceService sourceService;

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
