package ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TitleRepository extends CrudRepository<Title, Integer> {
    Iterable<Title> findAll();
    Optional<Title> getByTitleId(Integer id);

}


