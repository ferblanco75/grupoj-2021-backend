package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewPremium;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewReport;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Rates;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewRate;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.RateType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.ReviewRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.ReportRepository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewPremiumDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReportDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.UserDTO;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewService {

    @Autowired
    private  ReviewRepository reviewRepo;

    @Autowired
    private  LanguageService languageSrvc;

    @Autowired
    private TitleService titleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CriticService criticService;

    @Autowired
    private ReportRepository reportRepo;


    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        //save(new ReviewDTO());
        //User user = new User (source,"fernando.test@gmail.com","Fernando",location);
        //user.addReview(new Review(1, "Maso, para un domingo zafa","pochoclera",3,true,language));
        /*
        user.addReview(new Review(3, "Muy mala pelicula","No la entendi",1,true,language));
        user.addReview(new Review(5, "Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,language));
        */
        //userRepository.save(user);

/*
        Language language2 = new Language("English");
        Critic critic = new Critic(3,"criticoEspecialista@yahoo.com");
        critic.addReview(new ReviewPremium(4, "La mas pior!","Terriblemente aburrida.",5,true, language2));
        criticRepository.save(critic);
*/
    }

    public Iterable<Review> findAll() {
        return reviewRepo.findAll();
    }

    public Iterable<Review> findAllByIdTitle(Integer idTitle) throws ResourceNotFoundException {
        Title title = titleService.getByTitleId(idTitle).orElseThrow(() -> new ResourceNotFoundException("Non existent Title."));
        return reviewRepo.findAllByTitleId(title.getTitleId());
    }

    @Transactional
    public Review save(ReviewDTO aReview) throws NonExistentLocationException, NonExistentLanguageException, NonExistentSourceException, NonExistentTitleException, UserAlreadyReviewTitle {
        Language language= checkLanguage(aReview.languageId);

        //Creo u obtengo el usuario
        User user = userService.getBySourceAndUserIdAndNickId(aReview.user.sourceId,aReview.user.userId, aReview.user.userNick,aReview.languageId);

        //Guardo la review
        Review review = aReview.toModel(language,user);
        this.checkUniqueReviewer(review,user);
        reviewRepo.save(review);

        //Actualizo titulo con la nueva Review
        titleService.addReviewToTitle(review,aReview.titleId);

        return review;
    }

    private void checkUniqueReviewer(Review review, Critic user) throws UserAlreadyReviewTitle {
        if (reviewRepo.findReviewsByTitleIdAndUser(review.getTitleId(),user).size()>0) {
            throw new UserAlreadyReviewTitle(review.getTitleId(), user.getUniqueIdString());
        }
    }


    @Transactional
    public Review  savePremium(ReviewPremiumDTO aReview) throws NonExistentSourceException, NonExistentTitleException, NonExistentLanguageException, UserAlreadyReviewTitle {
        Language language= checkLanguage(aReview.languageId);
        //Creo u obtengo el usuario/critico
        Critic critic = criticService.getBySourceAndCriticId(aReview.critic.sourceId,aReview.critic.userId);

        //Guardo la review
        ReviewPremium review = aReview.toModel(language,critic);
        this.checkUniqueReviewer(review,critic);
        reviewRepo.save(review);

        //Actualizo titulo con la nueva Review
        titleService.addReviewToTitle(review,aReview.titleId);

        return review;

    }


    @Transactional
    public Rates rate(RateDTO rateDto) throws NonExistentReviewException, NonExistentSourceException, NonExistentLocationException {
        Review aReview= reviewRepo.findById(rateDto.reviewId).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        this.rateReview(aReview,rateDto.user,rateDto.rateType);
        reviewRepo.save(aReview);

        return aReview.getReviewRate();
    }

    public void rateReview(Review review, UserDTO user, RateType rateType) throws NonExistentLocationException, NonExistentSourceException {
        User rateUser = userService.getBySourceAndUserIdAndNickId(user.sourceId, user.userId, user.userNick,user.locationId);
        review.addRate(new ReviewRate(rateType,rateUser ,review));

    }


    private Language checkLanguage(Integer languageId) throws NonExistentLanguageException {
        return languageSrvc.getById(languageId);
    }

    @Transactional
    public ReviewReport report(ReportDTO reportDto) throws NonExistentReviewException, NonExistentLocationException, NonExistentSourceException {
        Review aReview= reviewRepo.findById(reportDto.reviewId).orElseThrow(() -> new NonExistentReviewException(reportDto.reviewId));
        User user= userService.getBySourceAndUserIdAndNickId(reportDto.user.sourceId, reportDto.user.userId, reportDto.user.userNick,reportDto.user.locationId);
        ReviewReport report= new ReviewReport(reportDto.reason,user);
        reportRepo.save(report);

        aReview.addReport(report);
        reviewRepo.save(aReview);
        return report;
    }

}
