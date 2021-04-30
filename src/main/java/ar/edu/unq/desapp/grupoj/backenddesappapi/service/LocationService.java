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
        locationRepo.save(new Location("argentina","bsas"));
        locationRepo.save(new Location("argentina","Mar del plata"));
        locationRepo.save(new Location("argentina","Rauch"));
        locationRepo.save(new Location("Brasil","Rio de Janeiro"));

    }

    public Iterable<Location> findAll() {
        return locationRepo.findAll();
    }
}
