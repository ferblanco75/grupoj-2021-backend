package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Suscription;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SuscriptionService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RegisterDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.SuscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration
public class SuscriptionController {

    @Autowired
    private SuscriptionService service;

    @CrossOrigin(origins ="*")
    @PostMapping("/suscription")
    public Suscription saveSuscription(@RequestBody SuscriptionDTO suscriptionDTO) {
        return service.save(suscriptionDTO.toModel(suscriptionDTO.getTitleId(), suscriptionDTO.getUrl()));
    }

    @GetMapping("/suscription/{url}")
    public ResponseEntity<List <Suscription>> callUrl(@PathVariable(value = "url") String url){
        List<Suscription> suscriptionsByUrl = service.getAllByUrl(url);
        return ResponseEntity.ok(suscriptionsByUrl);
    }

}
