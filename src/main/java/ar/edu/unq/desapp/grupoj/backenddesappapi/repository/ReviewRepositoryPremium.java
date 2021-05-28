package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewPremium;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepositoryPremium extends CrudRepository<ReviewPremium, Integer> {
    Iterable<ReviewPremium> findAll();

    Optional<ReviewPremium> findById(Integer integer);


    Iterable<ReviewPremium> findAllByTitleId(Integer idTitle);




}


