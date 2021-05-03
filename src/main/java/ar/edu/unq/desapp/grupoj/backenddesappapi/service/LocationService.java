package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class LocationService {

    private final LocationRepository locationRepo;

    @Autowired
    public LocationService(LocationRepository aRepository) {
        this.locationRepo = aRepository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        locationRepo.save(new Location("Argentina","Buenos Aires"));
        locationRepo.save(new Location("Argentina","Mar del Plata"));
        locationRepo.save(new Location("Argentina","Rosario"));
        locationRepo.save(new Location("Brasil","Rio de Janeiro"));
        locationRepo.save(new Location("Chile","Valparaiso"));

    }

    public Iterable<Location> findAll() {
        return locationRepo.findAll();
    }
}
