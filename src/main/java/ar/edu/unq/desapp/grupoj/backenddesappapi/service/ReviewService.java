package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReviewService {

    private final ReviewRepository reviewRepo;

    @Autowired
    public ReviewService(ReviewRepository unRepository) {
        this.reviewRepo = unRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        reviewRepo.save(new Review("Muy mala pelicula", 1));
        reviewRepo.save(new Review("Excelente, me conmovio! jaaaa", 5));
        reviewRepo.save(new Review("Pectacular, alta peli pero muy larga! ", 4));
    }

    @GetMapping("/")
    public Iterable<Review> getAllReviews() {
        return reviewRepo.findAll();
    }


}
