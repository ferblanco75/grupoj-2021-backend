package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentTitleException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Decade;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.DecadeRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.EpisodeRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.TitleRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.TitleRepositoryQueries;
import ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.InverseReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Service
public class TitleService {

    private TitleRepository titleRepo;
    private EpisodeRepository episodeRepo;
    @Autowired
    private DecadeRepository decadeRepo;

    @Autowired
    private TitleRepositoryQueries titleRepoQ;

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
        titleRepo.save(new Title(1,TitleType.MOVIE,"PREDATOR",false,2010,2010,100,genres));

        ArrayList<Genre> genres2= new ArrayList<>();
        genres2.add(Genre.ACTION);

        titleRepo.save(new Title(2,TitleType.TVSERIES,"LOST",false,2010,2011,40,genres2));

        ArrayList<Genre> genres3= new ArrayList<>();
        genres3.add(Genre.COMEDY);
        titleRepo.save(new Title(21,TitleType.TVEPISODE,"LOST: Chapter 1 'Pilot'",false,2014,2015,40,genres3));
    }
    public Optional<Title> getByTitleId(Integer id) {
        return this.titleRepo.getByTitleId(id);
    }

    public Iterable<Title> findAll() {
        return this.titleRepo.findAll();
    }

    public void addReviewToTitle(Review review, Integer titleId) throws NonExistentTitleException {
        Title title = getByTitleId(titleId).orElseThrow(() -> new NonExistentTitleException(titleId));
        title.addReview(review);
        titleRepo.save(title);
    }


    public List<Title> inverseQuery(InverseReq req) {
        List<Decade> decades= decadeRepo.getAllByIdIn(req.decade);
        return titleRepoQ.inverseQuery(req,decades);
    }
}
