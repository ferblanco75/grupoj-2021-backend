package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentLanguageException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class FrontUserController {

    @Autowired
    private FrontUserService service;

    @GetMapping("/frontusers")
    public Iterable<FrontUser> getAllFrontUsers() {
        return service.findAll();
    }


    @PostMapping("/register")
    public FrontUser saveUser(@RequestBody FrontUser frontuser) throws NonExistentSourceException, NonExistentLocationException, NonExistentLanguageException {
        return service.save(frontuser);
    }
}