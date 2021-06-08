package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.LocationService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

public class LocationController extends RestBaseController {

    @Autowired
    private LocationService service;

    @GetMapping("/location")
    public List<LocationDTO> getAll() {
        return service.findAll()
                .stream()
                .map(i->LocationDTO.fromModel(i))
                .collect(Collectors.toList());
    }


}
