package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface FrontUserRepository extends CrudRepository<FrontUser, Integer> {
        Iterable<FrontUser> findAll();
    }

