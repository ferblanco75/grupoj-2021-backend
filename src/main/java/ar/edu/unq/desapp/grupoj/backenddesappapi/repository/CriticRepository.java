package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CriticRepository extends CrudRepository<Critic, Integer> {
    Iterable<Critic> findAll();
    Optional<Critic> findByUserId(String userId);

}


