package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Suscription;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SuscriptionService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.SuscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/suscription/demo/{idTitle}")
    public void showActivity(@PathVariable(value = "idTitle") Integer idTitle) {
        System.out.print("RECIBI UNA NOTIFICACION PARA TITLEID = " + idTitle);
    }


}