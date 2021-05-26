package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import com.sun.xml.bind.v2.TODO;
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

        //TODO Actores y decada la leo de las peliculas filtradas



        //Las peliculas obtenidas de las reviews que tienen tales condiciones
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
