package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@CrossOrigin
@RestController
public class ReviewService {

    private final ReviewRepository reviewRepo;

    @Autowired
    public ReviewService(ReviewRepository unRepository) {
        this.reviewRepo = unRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        reviewRepo.save(new Review(1, new Source("Netflix"),"Maso, para un domingo zafa","pochoclera",3,true,"fernando.test@gmail.com","fer", new Location("Ecuador","Quito"),new Language("Spanish")));
        reviewRepo.save(new Review(1, new Source("Netflix"),"Muy mala pelicula","No la entendi",1,true,"alonso.em@gmail.com","quique", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        reviewRepo.save(new Review(2, new Source("Netflix"),"Excelente, me conmovio! jaaaa","Un plato",5,false,"alonso.em@gmail.com","rodolfo", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        reviewRepo.save(new Review(3, new Source("Netflix"),"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,"userAnonimo@gmail.com","pepe", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        reviewRepo.save(new ReviewPremium(3, new Source("Netflix"),"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,"userAnonimo@gmail.com","pepe", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        reviewRepo.save(new ReviewPremium(3, new Source("Netflix"),"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,"userAnonimo@gmail.com","pepe", new Location("Argentina","Buenos Aires"),new Language("Spanish")));

    }

    @GetMapping("/apiReviews")
    public Iterable<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    @GetMapping("/apiReviews/{idMovie}")
    public Iterable <Review> getReviewPorId(@PathVariable(value = "idMovie") Integer idMovie) throws ResourceNotFoundException{
        return reviewRepo.findAllByIdMovie(idMovie);
    }



}
