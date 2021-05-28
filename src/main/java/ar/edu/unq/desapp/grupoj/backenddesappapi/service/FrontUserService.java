package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentLanguageException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.FrontUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@CrossOrigin
@RestController
public class FrontUserService {
    @Autowired
    private FrontUserRepository frontUserRepo;

    public Iterable<FrontUser> findAll() {
        return frontUserRepo.findAll();
    }

    @Transactional
    public FrontUser save(FrontUser frontuser) throws NonExistentLocationException, NonExistentLanguageException, NonExistentSourceException {
        frontUserRepo.save(frontuser);
        return frontuser;
    }
}
