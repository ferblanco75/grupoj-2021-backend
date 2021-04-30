package ar.edu.unq.desapp.grupoj.backenddesappapi.service;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.ResourceNotFoundException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        User user = new User ("fernando.test@gmail.com","Fernando",language,location);
        user.addReview(new Review(1, source,"Maso, para un domingo zafa","pochoclera",3,true));
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
    public void save(ReviewAdapter aReview) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        //Deberia guardar o actualizar el usuario
        Location location= locationRepo.getById(aReview.locationId).orElseThrow(() -> new NonExistentLocationException(aReview.locationId));
        Language language= languageRepo.getById(aReview.languageId).orElseThrow(() -> new NonExistentLanguageException(aReview.languageId));
        Review review = aReview.toModel(sourceRepo,locationRepo,languageRepo);

        Optional<User> user=userRepository.findByUserIdAndUserNick(aReview.userId, aReview.userNick);

        User u = user.orElse(new User(aReview.userId,aReview.userNick,language,location));
        u.addReview(review);
        userRepository.save(u);
        //reviewRepo.save(review);
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
