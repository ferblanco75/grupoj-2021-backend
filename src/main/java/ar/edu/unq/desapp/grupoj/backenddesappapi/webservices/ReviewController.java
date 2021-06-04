package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReportDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewPremiumDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Date;

@RestController
@EnableAutoConfiguration
public class ReviewController {

    public final String datePattern = "yyyy/MM/dd";

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

    @GetMapping("/review2")
    // public Iterable <ReviewDTO> getReviewsByCriteria
    public ResponseEntity<Page<Review>> getReviews(ReviewPage reviewPage, ReviewSearchCriteria reviewSearchCriteria){

        return new ResponseEntity<>(service.getReviews(reviewPage,reviewSearchCriteria),HttpStatus.OK);
    }





}
