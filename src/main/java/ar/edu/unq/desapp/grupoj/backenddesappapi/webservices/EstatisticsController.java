package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Statistics;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
public class EstatisticsController {

    @Autowired
    private StatisticsService service;


    @GetMapping("/statistics/{platformId}")
    public ResponseEntity<List<Statistics>> getAllStatistics(@PathVariable("platformId") Integer platformId) {
        return ResponseEntity.ok(service.getAllStatisticsByPlatform(platformId));

    }

}
