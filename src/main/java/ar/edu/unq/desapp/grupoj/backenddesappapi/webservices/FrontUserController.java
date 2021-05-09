package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentLanguageException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
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