package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentReviewException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.TitleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@CrossOrigin
@RestController
public class ReviewService {

    @Autowired
    private  ReviewRepository reviewRepo;
    @Autowired
    private  SourceRepository sourceRepo;
    @Autowired
    private  LocationRepository locationRepo;
    @Autowired
    private  LanguageRepository languageRepo;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CriticRepository criticRepository;

    @Autowired
    private TitleRepository titleRepo;

    public ReviewService(){ }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        Source source =new Source("Netflix");

        Location location = new Location("Ecuador","Quito");
        Language language = new Language("Spanish");

        User user = new User (1,"fernando.test@gmail.com","Fernando",location);
        user.addReview(new Review(1, "Maso, para un domingo zafa","pochoclera",3,true,language));
        user.addReview(new Review(3, "Muy mala pelicula","No la entendi",1,true,language));
        user.addReview(new Review(5, "Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,language));
        userRepository.save(user);


        Language language2 = new Language("English");
        Critic critic = new Critic(3,"criticoEspecialista@yahoo.com");
        critic.addReview(new ReviewPremium(4, "La mas pior!","Terriblemente aburrida.",5,true, language2));
        criticRepository.save(critic);

    }

    public Iterable<Review> findAll() {
        return reviewRepo.findAll();
    }

    public Iterable<Review> findAllByIdTitle(Integer idTitle) throws ResourceNotFoundException {
        Title title = titleRepo.getByTitleId(idTitle).orElseThrow(() -> new ResourceNotFoundException("Non existent Title."));;
        return reviewRepo.findAllByTitleId(title.getTitleId());
    }

    @Transactional
    public Review save(ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException {
        Location location= locationRepo.getById(aReview.user.locationId).orElseThrow(() -> new NonExistentLocationException(aReview.user.locationId));
        Review review = aReview.toModel(sourceRepo,languageRepo);

        User user=userRepository.findBySourceIdAndUserIdAndUserNick(aReview.user.sourceId,aReview.user.userId, aReview.user.userNick)
                .orElse(new User(aReview.user.sourceId, aReview.user.userId,aReview.user.userNick,location));

        user.addReview(review);
        userRepository.save(user);
        return review;
    }

    @Transactional
    public Review  savePremium(ReviewPremiumDTO aReview) throws NonExistentSourceException {

        ReviewPremium review = aReview.toModel(languageRepo);

        Critic critic=criticRepository.findBySourceIdAndUserId(aReview.critic.sourceId,aReview.critic.userId)
                .orElse(new Critic(aReview.critic.sourceId,aReview.critic.userId));

        critic.addReview(review);

        criticRepository.save(critic);
        return review;

    }


    @Transactional
    public Rates rate(RateDTO rateDto) throws NonExistentLocationException, NonExistentReviewException {
        Review aReview= reviewRepo.findById(rateDto.reviewId).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        User user;
        try {
            user = userRepository.findBySourceIdAndUserIdAndUserNick(rateDto.user.sourceId ,rateDto.user.userId, rateDto.user.userNick).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        }catch (Exception e){

            user = rateDto.user.toModel(locationRepo);

        }
        aReview.addRate(new ReviewRate(rateDto.rateType,user,aReview));

        reviewRepo.save(aReview);
        return aReview.getReviewRate();


    }
}
