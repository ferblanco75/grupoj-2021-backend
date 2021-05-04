package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping("/review")
    public Iterable<Review> getAllReviews() {
        return service.findAll();
    }

    @PutMapping("/review")
    public void saveReview(@RequestBody ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        service.save(aReview);
    }

    @GetMapping("/review/{idTitle}")
    public Iterable <Review> getReviewPorId(@PathVariable(value = "idTitle") Integer idTitle) throws ResourceNotFoundException {
        return service.findAllByIdTitle(idTitle);
    }

    @PutMapping("/review/premium")
    public void savePremiumReview(@RequestBody ReviewPremiumDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        service.savePremium(aReview);
    }

    @PostMapping("/review/rate")
    public ReviewRate rateUpPlus(@RequestBody RateDTO rateDto) throws NonExistentReviewException, NonExistentLocationException {
        return service.rateUpPlus(rateDto);
    }

}
