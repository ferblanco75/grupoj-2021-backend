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

    @GetMapping("/review/{idMovie}")
    public Iterable <Review> getReviewPorId(@PathVariable(value = "idMovie") Integer idMovie) throws ResourceNotFoundException {
        return service.findAllByIdMovie(idMovie);
    }

    @PutMapping("/review")
    public void saveReview(@RequestBody ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        service.save(aReview);
    }

    @PutMapping("/review/premium")
    public void savePremiumReview(@RequestBody ReviewPremiumDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        service.savePremium(aReview);
    }

    /*@PostMapping("/review/{idReview}/RateUp")
    public ReviewRate rateUp(@PathVariable(value = "idReview") Integer idReview) throws NonExistentReviewException{
        return service.rateUp(idReview);
    }

    @PostMapping("/review/{idReview}/RateDown")
    public ReviewRate rateDown(@PathVariable(value = "idReview") Integer idReview) throws NonExistentReviewException{
        return service.rateDown(idReview);
    }
*/
    @PostMapping("/review/RateUpPlus")
    public ReviewRate rateUpPlus(@RequestBody RateDTO rateDto) throws NonExistentReviewException, NonExistentLocationException {
        return service.rateUpPlus(rateDto);
    }

}
