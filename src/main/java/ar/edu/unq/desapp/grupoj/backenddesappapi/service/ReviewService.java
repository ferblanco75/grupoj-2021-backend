package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.converter.ReviewConverter;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.*;

@Service
public class ReviewService {

    @Autowired
    private  ReviewRepository reviewRepo;

    @Autowired
    private  LanguageRepository languageRepo;

    @Autowired
    private TitleService titleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CriticService criticService;

    @Autowired
    private ReportRepository reportRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SourceRepository sourceRepo;

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private  ReviewCriteriaRepository reviewCriteriaRepository;


    public ReviewService(){ }

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
        return reviewRepo.findAllByOrderByDateDesc();
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

    public void rateReview(Review review, UserDTO user,RateType rateType) throws NonExistentLocationException, NonExistentSourceException {
        User rateUser = userService.getBySourceAndUserIdAndNickId(user.sourceId, user.userId, user.userNick,user.locationId);
        review.addRate(new ReviewRate(rateType,rateUser ,review));

    }


    private Language checkLanguage(Integer languageId) throws NonExistentLanguageException {
        return languageRepo.getById(languageId).orElseThrow(() -> new NonExistentLanguageException(languageId));
    }

    private Language getLanguageByName(String languageName) throws NonExistentLanguageNameException {
        return languageRepo.findByValue(languageName).orElseThrow(() -> new NonExistentLanguageNameException(languageName));
    }


    private Iterable<User> getAllUsersBySource(Source source) throws ResourceNotFoundException {
        return userRepo.findAllBySource(source);
    }

    private Iterable<User> getAllUsersByLocation(Location location) throws ResourceNotFoundException {
        return userRepo.findAllByLocation(location);
    }

    private Source getSourceByName(String sourceName) throws ResourceNotFoundException {
        return sourceRepo.getByName(sourceName).orElseThrow(() -> new ResourceNotFoundException("not found review"));
    }


    private Iterable <Location> getLocationsByName(String country) throws ResourceNotFoundException {
        return locationRepo.findAllByCountry(country);
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

    public Iterable<Review> findAllBySpoilerAlert(boolean spoilerAlert) throws NonExistentReviewException {
        return  reviewRepo.findAllBySpoilerAlert(spoilerAlert);
    }

    public Iterable<Review> findAllByLanguage(String language) throws NonExistentReviewException, NonExistentLanguageException, NonExistentLanguageNameException {
        Language lang = getLanguageByName(language);
        return  reviewRepo.findAllByLanguage(lang);
    }

    public Iterable<Review> findAllByTypeNormal(String type) throws ResourceNotFoundException {
        ReviewType reviewType = ReviewType.NORMAL;
        return  reviewRepo.findAllByType(reviewType);
    }

    public Iterable<Review> findAllByTypeCritic(String type) throws ResourceNotFoundException {
        ReviewType reviewType = ReviewType.PREMIUM;
        return  reviewRepo.findAllByType(reviewType);
    }

    public Iterable<Review> findAllByUserInSource(String sourceName) throws ResourceNotFoundException{
        Source source = getSourceByName(sourceName);
        Iterable <User> users = getAllUsersBySource(source);
        Iterable<Review> reviews = new ArrayList<Review>();
        for (User user:users ) {
            reviews = reviewRepo.findAllByUser(user);
        }
        return reviews;
    }

    public Iterable<Review> findAllByUserInCountry(String country) throws ResourceNotFoundException{
       Iterable <Location> locations = getLocationsByName(country);
        Iterable <User> users = new ArrayList<User>();
        for (Location location:locations){
           users = userRepo.findAllByLocation(location);
        }
        Iterable<Review> reviews = new ArrayList<Review>();
        for (User user:users ) {
            reviews = reviewRepo.findAllByUser(user);
        }
        return reviews;
    }


    public Iterable<Review> getAllByIdOrderByDate(Integer id) throws ResourceNotFoundException {
            return reviewRepo.findAllByIdOrderByDateDesc(id);
    }




    //ATENCION EL Service debe pasarle un DTO al controller
    //hay que hacer una implementacion toDTO antes
    //

    public Page<Review> getReviews(ReviewPage reviewPage, ReviewSearchCriteria reviewSearchCriteria){
        return reviewCriteriaRepository.FindAllWithFilters(reviewPage, reviewSearchCriteria);

    }
}





