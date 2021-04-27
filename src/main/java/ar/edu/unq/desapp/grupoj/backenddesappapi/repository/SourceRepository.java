package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SourceRepository extends CrudRepository<Source, Integer> {
    Iterable<Source> findAll();
    Optional<Source> getById(Integer id);

}


