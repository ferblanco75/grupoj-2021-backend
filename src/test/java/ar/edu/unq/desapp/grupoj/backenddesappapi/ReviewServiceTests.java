package ar.edu.unq.desapp.grupoj.backenddesappapi;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.ReviewService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ReviewServiceTests {

    @Autowired
    ReviewService reviewService;

    @Test
    void  retrieveAllReviewsAndGetNone () throws NonExistentTitleException {
        List<Review> reviews = reviewService.findAllByIdTitle(1);
        assertEquals(2,reviews.size());
    }
    @Test
    void  addOneReviewAndThenGetAllReviews () throws NonExistentSourceException, UserAlreadyReviewTitle, NonExistentLanguageException, NonExistentLocationException, NonExistentTitleException {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.languageId=1;
        reviewDTO.rating=4;
        reviewDTO.spoilerAlert=false;
        reviewDTO.titleId=1;
        reviewDTO.type= ReviewType.NORMAL;
        reviewDTO.user=new UserDTO(3,"DummyUser","bla",1);

        reviewService.save(reviewDTO);
        List<Review> reviews = reviewService.findAllByIdTitle(1);
        assertEquals(3,reviews.size());
    }

    @Test
    void  addOnePremiumReviewAndThenGetAllReviews () throws NonExistentSourceException, UserAlreadyReviewTitle, NonExistentLanguageException, NonExistentLocationException, NonExistentTitleException {
         ReviewPremiumDTO reviewDTO = new ReviewPremiumDTO();
        reviewDTO.languageId=1;
        reviewDTO.rating=4;
        reviewDTO.spoilerAlert=false;
        reviewDTO.titleId=1;
        reviewDTO.type= ReviewType.NORMAL;
        reviewDTO.critic=new CriticDTO(2,"bla");


        reviewService.savePremium(reviewDTO);
        List<Review> reviews = reviewService.findAllByIdTitle(1);
        assertEquals(4,reviews.size());
    }

    @Test
    void  getAllReviewsNotJustTitleNumber1 () throws NonExistentSourceException, UserAlreadyReviewTitle, NonExistentLanguageException, NonExistentLocationException, NonExistentTitleException {
        Language language = Mockito.mock(Language.class);
        User user = Mockito.mock(User.class);

        ReviewPremiumDTO reviewDTO = new ReviewPremiumDTO();
        reviewDTO.languageId=1;
        reviewDTO.rating=4;
        reviewDTO.spoilerAlert=false;
        reviewDTO.titleId=2;
        reviewDTO.type= ReviewType.NORMAL;
        reviewDTO.critic=new CriticDTO(3,"bla");


        reviewService.savePremium(reviewDTO);
        List<Review> reviews = reviewService.findAll();
        assertEquals(4,reviews.size());
    }

    @Test
    void  rateReviewAndGetEstatistics () throws NonExistentSourceException, NonExistentLocationException, NonExistentReviewException, NonExistentTitleException, UserAlreadyReviewTitle, NonExistentLanguageException {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.languageId=1;
        reviewDTO.rating=4;
        reviewDTO.spoilerAlert=false;
        reviewDTO.titleId=1;
        reviewDTO.type= ReviewType.NORMAL;
        reviewDTO.user=new UserDTO(1,"bla","bla",1);

        Review review =reviewService.save(reviewDTO);

        UserDTO user = new UserDTO(1,"quique","pepe",1);
        RateDTO rate = new RateDTO(user,review.getId(),RateType.UP);

        Rates reviewRates = reviewService.rate(rate);


        assertEquals(1,reviewRates.getRatingUp());
        assertEquals(0,reviewRates.getRatingDown());
    }

    @Test
    void  reportReviewAndMatch () throws NonExistentSourceException, NonExistentLocationException, NonExistentReviewException, NonExistentTitleException, UserAlreadyReviewTitle, NonExistentLanguageException {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.languageId=1;
        reviewDTO.rating=4;
        reviewDTO.spoilerAlert=false;
        reviewDTO.titleId=1;
        reviewDTO.type= ReviewType.NORMAL;
        reviewDTO.user=new UserDTO(1,"pipo","bla",1);

        reviewService.save(reviewDTO);

        UserDTO user = new UserDTO(1,"quique","pepe",1);

        ReportDTO report= new ReportDTO(1,Reason.OFFENSIVE,"BLA BLA",user);

        ReviewReport reviewReport= reviewService.report(report);

        assertEquals(Reason.OFFENSIVE,reviewReport.getReason());
        assertEquals("pepe",reviewReport.getUser().getUserNick());
    }

}
