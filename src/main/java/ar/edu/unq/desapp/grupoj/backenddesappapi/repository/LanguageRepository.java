package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<Language, Integer> {
    Iterable<Language> findAll();
    Optional<Language> getById(Integer id);

}


