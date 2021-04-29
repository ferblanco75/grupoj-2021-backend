package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    Iterable<Review> findAll();

    Iterable<Review> getAllByIdGreaterThanEqual(Integer rating);

    Iterable <Review> findAllByIdMovie(Integer idMovie);

    Optional<Review> findById(Integer integer);
}


