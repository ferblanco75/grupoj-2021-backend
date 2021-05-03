package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;


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

    public ReviewService(){

    }/*
    @Autowired
    public ReviewService(ReviewRepository unRepository, SourceRepository sourceRepository,LocationRepository locationRepository, LanguageRepository languageRepository) {
        this.reviewRepo = unRepository;
        this.sourceRepo = sourceRepository;
        this.locationRepo = locationRepository;
        this.languageRepo = languageRepository;
    }*/

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        Source source =new Source("Netflix");
        //sourceRepo.save(source);
        Location location = new Location("Ecuador","Quito");
        Language language = new Language("Spanish");
        User user = new User ("fernando.test@gmail.com","Fernando",location);
        user.addReview(new Review(1, source,"Maso, para un domingo zafa","pochoclera",3,true,language));

        user.addReview(new Review(2, source,"Maso, para un domingo zafa","pochoclera",3,true,language));

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

    public Iterable<Review> findAllByIdMovie(Integer idMovie) {
        return reviewRepo.findAllByIdMovie(idMovie);
    }

    @Transactional
    public void save(ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException{
        //Deberia guardar o actualizar el usuario
        Location location= locationRepo.getById(aReview.locationId).orElseThrow(() -> new NonExistentLocationException(aReview.locationId));
        Review review = aReview.toModel(sourceRepo,languageRepo);

        Optional<User> user=userRepository.findByUserIdAndUserNick(aReview.userId, aReview.userNick);

        User u = user.orElse(new User(aReview.userId,aReview.userNick,location));
        u.addReview(review);
        userRepository.save(u);
    }

    @Transactional
    public void savePremium(ReviewPremiumDTO aReview) throws NonExistentSourceException {

        ReviewPremium review = aReview.toModel(sourceRepo,languageRepo);

        Optional<Critic> user=criticRepository.findByUserId(aReview.userId);

        Critic c = user.orElse(new Critic(aReview.userId));
        c.addReview(review);

        criticRepository.save(c);

    }


    @Transactional
    public ReviewRate rateUpPlus(RateDTO rateDto) throws NonExistentLocationException, NonExistentReviewException {
        Review r= reviewRepo.findById(rateDto.reviewId).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        User user;
        try {
            user = userRepository.findByUserIdAndUserNick(rateDto.user.userId, rateDto.user.userNick).orElseThrow(() -> new NonExistentReviewException(rateDto.reviewId));
        }catch (Exception e){

            user = rateDto.user.toModel(locationRepo);

        }
        r.addRate(new ReviewRatePlus (rateDto.rateType,user,r));


        //TODO Hacer la excepcion de User

        //Quiero grabar usuario y actualizar location?
        //userRepository.save(user);
        reviewRepo.save(r);
        return r.getReviewRate();


    }
}
