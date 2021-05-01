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
        Location location = new Location("Ecuador","Quito");
        Language language = new Language("Spanish");
        User user = new User ("fernando.test@gmail.com","Fernando",location);
        user.addReview(new Review(1, source,"Maso, para un domingo zafa","pochoclera",3,true,language));
        userRepository.save(user);
        //reviewRepo.save(new Review(1, source,"Maso, para un domingo zafa","pochoclera",3,true));
        //reviewRepo.save(new Review(1, source,"Muy mala pelicula","No la entendi",1,true,"alonso.em@gmail.com","quique", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        //reviewRepo.save(new Review(2, source,"Excelente, me conmovio! jaaaa","Un plato",5,false,"alonso.em@gmail.com","rodolfo", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        //reviewRepo.save(new Review(3, new Source("Netflix"),"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,"userAnonimo@gmail.com","pepe", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        //reviewRepo.save(new ReviewPremium(3, new Source("Netflix"),"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,"userAnonimo@gmail.com","pepe", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
        //reviewRepo.save(new ReviewPremium(3, new Source("Netflix"),"Pectacular, alta peli pero muy larga!","Increibles efecto especiales",3,false,"userAnonimo@gmail.com","pepe", new Location("Argentina","Buenos Aires"),new Language("Spanish")));
    }

    public Iterable<Review> findAll() {
        return reviewRepo.findAll();
    }

    public Iterable<Review> findAllByIdMovie(Integer idMovie) {
        return reviewRepo.findAllByIdMovie(idMovie);
    }

    @Transactional
    public void save(ReviewDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        //Deberia guardar o actualizar el usuario
        Location location= locationRepo.getById(aReview.locationId).orElseThrow(() -> new NonExistentLocationException(aReview.locationId));
        Review review = aReview.toModel(sourceRepo,languageRepo);

        Optional<User> user=userRepository.findByUserIdAndUserNick(aReview.userId, aReview.userNick);

        User u = user.orElse(new User(aReview.userId,aReview.userNick,location));
        u.addReview(review);
        userRepository.save(u);
    }

    @Transactional
    public void savePremium(ReviewPremiumDTO aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {

        ReviewPremium review = aReview.toModel(sourceRepo,languageRepo);

        Optional<Critic> user=criticRepository.findByUserId(aReview.userId);

        Critic c = user.orElse(new Critic(aReview.userId));
        c.addReview(review);

        criticRepository.save(c);

    }

    public ReviewRate rateUp( Integer idReview) throws NonExistentReviewException{
        Review r= reviewRepo.findById(idReview).orElseThrow(() -> new NonExistentReviewException(idReview));
        r.getReviewRate().rateUp();
        reviewRepo.save(r);
        return r.getReviewRate();
    }



    public ReviewRate rateDown(Integer idReview) throws NonExistentReviewException{
        Review r= reviewRepo.findById(idReview).orElseThrow(() -> new NonExistentReviewException(idReview));
        r.getReviewRate().rateDown();
        reviewRepo.save(r);
        return r.getReviewRate();
    }
}
