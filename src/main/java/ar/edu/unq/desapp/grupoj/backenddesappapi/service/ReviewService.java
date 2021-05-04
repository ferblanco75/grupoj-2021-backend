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

        User user = new User ("fernando.test@gmail.com","Fernando",location);
        user.addReview(new Review(1, source,"Maso, para un domingo zafa","pochoclera",3,true,language));

        user.addReview(new Review(3, source,"Muy mala pelicula","No la entendi",1,true,language));
        user.addReview(new Review(4, source,"Excelente, me conmovio! jaaaa","Un plato",5,false,language));
        user.addReview(new Review(5, source,"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,language));

        Critic critic = new Critic("criticoEspecialista@yahoo.com");
        critic.addReview(new ReviewPremium(3, source,"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,language));
        critic.addReview(new ReviewPremium(1, source,"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",5,true, language));
        userRepository.save(user);
    }

    public Iterable<Review> findAll() {
        return reviewRepo.findAll();
    }

    public Iterable<Review> findAllByIdTitle(Integer idTitle) throws ResourceNotFoundException {
        Title title = titleRepo.getByTitleId(idTitle).orElseThrow(() -> new ResourceNotFoundException("Non existent Title."));;
        return reviewRepo.findAllByTitleId(title.getTitleId());
    }

    @Transactional
    public void save(ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException {
        Location location= locationRepo.getById(aReview.locationId).orElseThrow(() -> new NonExistentLocationException(aReview.locationId));
        Review review = aReview.toModel(sourceRepo,languageRepo);

        User user=userRepository.findByUserIdAndUserNick(aReview.userId, aReview.userNick)
                .orElse(new User(aReview.userId,aReview.userNick,location));

        user.addReview(review);
        userRepository.save(user);
    }

    @Transactional
    public void savePremium(ReviewPremiumDTO aReview) throws NonExistentSourceException {

        ReviewPremium review = aReview.toModel(sourceRepo,languageRepo);

        Critic critic=criticRepository.findByUserId(aReview.userId)
                .orElse(new Critic(aReview.userId));

        critic.addReview(review);

        criticRepository.save(critic);

    }


    @Transactional
    public ReviewRate rate(RateDTO rateDto) throws NonExistentLocationException, NonExistentReviewException {
        Review r= reviewRepo.findById(rateDto.reviewId).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        User user;
        try {
            user = userRepository.findByUserIdAndUserNick(rateDto.user.userId, rateDto.user.userNick).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        }catch (Exception e){

            user = rateDto.user.toModel(locationRepo);

        }
        r.addRate(new ReviewRatePlus (rateDto.rateType,user,r));

        reviewRepo.save(r);
        return r.getReviewRate();


    }
}
