package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.InverseReq;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.TitleDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentTitleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/title")
    public List<TitleDTO> getAll() {
        return titleService.findAll()
                .stream()
                .map(i->TitleDTO.fromModel(i))
                .collect(Collectors.toList());
    }

    @GetMapping("/title/{id}")
    public TitleDTO getById(@PathVariable(value = "id") Integer id) throws NonExistentTitleException {
        return TitleDTO.fromModel(titleService.getByTitleId(id));
    }

    @PostMapping("/title/inverse")
    public List<TitleDTO> getAllMatching(@RequestBody InverseReq req) {
        List<Title> titles = titleService.inverseQuery(req);

        return titles.stream()
                .map(i -> TitleDTO.fromModel(i))
                .collect(Collectors.toList());
    }

    //return ResponseEntity.ok().body(resultado);
}


