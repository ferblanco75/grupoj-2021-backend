package ar.edu.unq.desapp.grupoj.backenddesappapi.helper;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository.TitleRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.ReviewDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.ReviewPremiumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@CrossOrigin
@RestController
public class TestService {

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



    public TestService(){ }

   public void crearDatosIniciales(){

       Language language1 = new Language("English");
       Review review1= new Review(1,"prueba 1", "Detalle de prueba 1", 4, true,language1);
       Source source = new Source("CINEAR");
       Location location = new Location("Argentina", "Bernal");
       User user = new User (source,"enrique.alonso@unq.edu.ar","Quique",location);
       user.addReview(review1);
       userRepository.save(user);

   }
   public void eliminarTodo(){
        userRepository.deleteAll();
        reviewRepo.deleteAll();
       sourceRepo.deleteAll();
   }

}
