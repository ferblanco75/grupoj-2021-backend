package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Estatistics;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.EstatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
public class EstatisticsController {

    @Autowired
    private EstatisticsService service;


    @GetMapping("/estatistics")
    public List<Estatistics> getAllEstatistics() {
        return service.getAllEstatistics();

    }

}
