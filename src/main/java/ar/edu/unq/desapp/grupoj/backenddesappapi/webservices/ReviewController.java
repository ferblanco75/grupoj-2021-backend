package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.ReviewService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReportDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewPremiumDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
@Validated
public class ReviewController {

    public final String datePattern = "yyyy/MM/dd";

    @Autowired
    private ReviewService service;

    @CrossOrigin(origins ="*",methods = RequestMethod.GET, allowedHeaders = "*")
    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviews() {
        return new ResponseEntity(service.findAll(),HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<?> saveReview(@Valid @RequestBody ReviewDTO aReview) throws NonExistentSourceException, UserAlreadyReviewTitle, NonExistentLanguageException, NonExistentTitleException, NonExistentLocationException {
        return new ResponseEntity(service.save(aReview),HttpStatus.CREATED);
    }


    @GetMapping("/review/{idTitle}")
    public ResponseEntity<List<Review>> getReviewPorId(@PathVariable(value = "idTitle") Integer idTitle) throws NonExistentTitleException {
        return new ResponseEntity(service.findAllByIdTitle(idTitle)
                                ,HttpStatus.OK);
    }

    @PostMapping("/review/premium")
    public ResponseEntity<Review> savePremiumReview(@RequestBody ReviewPremiumDTO aReview) throws NonExistentSourceException, NonExistentLanguageException, NonExistentTitleException, UserAlreadyReviewTitle, NonExistentLocationException, NonExistentCriticException {
        return new ResponseEntity(service.savePremium(aReview),HttpStatus.CREATED);
    }

    @PutMapping("/review/rate")
    public ResponseEntity<Rates> rate(@RequestBody RateDTO rateDto) throws NonExistentReviewException, NonExistentLocationException, NonExistentSourceException, NonExistentUserException {
        return new ResponseEntity(
                                service.rate(rateDto)
                                ,HttpStatus.CREATED);
    }

    @PutMapping("/review/report")
    public ResponseEntity<?> rate(@RequestBody ReportDTO jsonReport) throws NonExistentReviewException, NonExistentLocationException, NonExistentSourceException {
        return new ResponseEntity(service.report(jsonReport), HttpStatus.CREATED);
    }

    @GetMapping("/review/report")
    public ResponseEntity<List<ReviewReport>> reviewReports(){
        return ResponseEntity.ok(service.findAllReports());
    }


    @GetMapping("/review2")
    public ResponseEntity<Page<Review>> getReviews(ReviewPage reviewPage, ReviewSearchCriteria reviewSearchCriteria){
        return new ResponseEntity<>(service.getReviews(reviewPage,reviewSearchCriteria),HttpStatus.OK);
    }


}
