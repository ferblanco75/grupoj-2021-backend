package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.*;
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
    public void appReady(ApplicationReadyEvent event) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {

        save(new ReviewDTO());
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
        Title title = titleRepo.getByTitleId(idTitle).orElseThrow(() -> new ResourceNotFoundException("Non existent Title."));;
        return reviewRepo.findAllByTitleId(title.getTitleId());
    }

    @Transactional
    public Review save(ReviewDTO aReview) throws NonExistentLocationException, NonExistentLanguageException, NonExistentSourceException {
        Location location= locationRepo.getById(aReview.user.locationId).orElseThrow(() -> new NonExistentLocationException(aReview.user.locationId));
        Source source = sourceRepo.getById(aReview.user.sourceId).orElseThrow(() -> new NonExistentSourceException(aReview.user.sourceId));
        Review review = aReview.toModel(languageRepo);

        User user=userRepository.findBySourceAndUserIdAndUserNick(source,aReview.user.userId, aReview.user.userNick)
                .orElse(new User(source, aReview.user.userId,aReview.user.userNick,location));

        user.addReview(review);
        userRepository.save(user);
        return review;
    }

    @Transactional
    public Review  savePremium(ReviewPremiumDTO aReview) throws NonExistentSourceException {
        Source source = sourceRepo.getById(aReview.critic.sourceId).orElseThrow(() -> new NonExistentSourceException(aReview.critic.sourceId));

        ReviewPremium review = aReview.toModel(languageRepo);

        Critic critic=criticRepository.findBySourceAndUserId(source,aReview.critic.userId)
                .orElse(new Critic(source, aReview.critic.userId));

        critic.addReview(review);

        criticRepository.save(critic);
        return review;

    }


    @Transactional
    public Rates rate(RateDTO rateDto) throws NonExistentLocationException, NonExistentReviewException, NonExistentSourceException {
        Review aReview= reviewRepo.findById(rateDto.reviewId).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        Source source= sourceRepo.findById(rateDto.user.sourceId).orElseThrow(() -> new NonExistentSourceException(rateDto.user.sourceId));
        Location location= locationRepo.findById(rateDto.user.locationId).orElseThrow(() -> new NonExistentLocationException(rateDto.user.locationId));
        User user = userRepository.findBySourceAndUserIdAndUserNick(source ,rateDto.user.userId, rateDto.user.userNick).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));

        aReview.addRate(new ReviewRate(rateDto.rateType,user,aReview));

        reviewRepo.save(aReview);
        return aReview.getReviewRate();


    }
}
