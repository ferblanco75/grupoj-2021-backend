package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Estatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstatisticsRepository extends CrudRepository<Estatistics, String> {
    List<Estatistics> findAll();
    Optional<Estatistics> findById(Id id);



}


