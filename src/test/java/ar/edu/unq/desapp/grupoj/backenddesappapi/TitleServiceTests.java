package ar.edu.unq.desapp.grupoj.backenddesappapi;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentTitleException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TitleServiceTests {

    @Autowired
    TitleService titleService;

    @Test
    void retrieveAllTitlesAndGetNone(){
        List<Title> titles = titleService.findAll();
        assertEquals(3,titles.size());
    }

    @Test
    void retrieveAllTitlesAndGetOne() throws NonExistentTitleException {
        Title title = titleService.getByTitleId(1);
        assertEquals("PREDATOR",title.getTitle());
    }

}
