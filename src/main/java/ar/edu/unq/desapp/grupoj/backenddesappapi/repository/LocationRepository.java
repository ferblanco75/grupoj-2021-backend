package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    Iterable<Location> findAll();
    Optional<Location> getById(Integer id);

    Iterable<Location> findAllByCountry(String country);
}


