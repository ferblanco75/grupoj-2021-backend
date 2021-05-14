package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewPremium;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepositoryPremium extends CrudRepository<ReviewPremium, Integer> {
    Iterable<ReviewPremium> findAll();

    Optional<ReviewPremium> findById(Integer integer);


    Iterable<ReviewPremium> findAllByTitleId(Integer idTitle);




}


