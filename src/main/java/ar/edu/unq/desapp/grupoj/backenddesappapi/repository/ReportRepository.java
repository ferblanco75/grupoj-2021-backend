package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReportRepository extends CrudRepository<ReviewReport, Integer> {
    Iterable<ReviewReport> findAll();
    Optional<ReviewReport> getById(Integer id);

}


