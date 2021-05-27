package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.*;
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

    @PostMapping("/review")
    public Review saveReview(@RequestBody ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException, NonExistentTitleException, UserAlreadyReviewTitle {
        return service.save(aReview);
    }

    @GetMapping("/review/{idTitle}")
    public Iterable <Review> getReviewPorId(@PathVariable(value = "idTitle") Integer idTitle) throws ResourceNotFoundException {
        return service.findAllByIdTitle(idTitle);
    }

    @PostMapping("/review/premium")
    public Review savePremiumReview(@RequestBody ReviewPremiumDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException, NonExistentTitleException, UserAlreadyReviewTitle {
        return service.savePremium(aReview);
    }

    @PutMapping("/review/rate")
    public Rates rate(@RequestBody RateDTO rateDto) throws NonExistentReviewException, NonExistentLocationException, NonExistentSourceException, NonExistentUserException {
        return service.rate(rateDto);
    }

    @PutMapping("/review/report")
    public ReviewReport rate(@RequestBody ReportDTO jsonReport) throws NonExistentReviewException, NonExistentLocationException, NonExistentSourceException, NonExistentUserException {
        return service.report(jsonReport);
    }

    @GetMapping("/reviewBySpoiler/{hasSpoiler}")
    public Iterable <Review> getReviewPorSpoilerAlert(@PathVariable(value = "hasSpoiler") boolean spoilerAlert) throws ResourceNotFoundException, NonExistentReviewException {
        return service.findAllBySpoilerAlert(spoilerAlert);
    }

    @GetMapping("/reviewBylanguage/{language}")
    public Iterable <Review> getReviewByLanguage(@PathVariable(value = "language") String language) throws ResourceNotFoundException, NonExistentReviewException, NonExistentLanguageException, NonExistentLanguageNameException {
        return service.findAllByLanguage(language);
    }


}
