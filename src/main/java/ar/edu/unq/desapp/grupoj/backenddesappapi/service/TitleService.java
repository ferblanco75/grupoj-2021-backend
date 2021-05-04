package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.EpisodeRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@CrossOrigin
@RestController
public class TitleService {

    private TitleRepository titleRepo;
    private EpisodeRepository episodeRepo;

    @Autowired
    public TitleService(TitleRepository aRepository, EpisodeRepository episodeRepo) {
        this.titleRepo = aRepository;
        this.episodeRepo= episodeRepo;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        ArrayList<Genre> genres= new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.DRAMA);

        titleRepo.save(new Title(1,TitleType.MOVIE,"PREDATOR",false,new Date("01/01/2010"),new Date("01/01/2010"),100,genres));
        titleRepo.save(new Title(2,TitleType.TVSERIES,"LOST",false,new Date("01/01/2010"),new Date("01/01/2010"),40,genres));

        titleRepo.save(new Title(21,TitleType.TVEPISODE,"LOST: Chapter 1 'Pilot'",false,new Date("01/01/2010"),new Date("01/01/2010"),40,genres));



    }
    public Optional<Title> getByTitleId(Integer id) {
        return this.titleRepo.getByTitleId(id);
    }

    public Iterable<Title> findAll() {
        return this.titleRepo.findAll();
    }




}
