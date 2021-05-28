package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs.InverseReq;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs.TitleDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/title/inverse")
    public List<TitleDTO> getAllMatching(@RequestBody InverseReq req) {

        List<Title> t = titleService.inverseQuery(req);

        return t.stream().map(i ->
                new TitleDTO(
                    i.getTitleId(),
                    i.getTitle(),
                    i.getDuration(),
                    i.getTitleType(),
                    i.getStartYear(),
                    i.getEndYear(),
                    i.getAdult()
                )
        ).collect(Collectors.toList());

    }




}
