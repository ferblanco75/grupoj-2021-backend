package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.InverseReq;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.TitleDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentTitleException;
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
    public Title getById(@PathVariable(value = "id") Integer id) throws NonExistentTitleException {
        return titleService.getByTitleId(id);
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
