package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.LanguageService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.LanguageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
public class LanguageController {


    private LanguageService service;

    @Autowired
    public LanguageController(LanguageService language){
        service=language;
    }

    @GetMapping("/language")
    public List<LanguageDTO> getAll() {
        return service.findAll()
                .stream()
                .map(i->LanguageDTO.fromModel(i))
                .collect(Collectors.toList());
    }


}
