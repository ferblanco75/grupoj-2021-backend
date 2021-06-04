package ar.edu.unq.desapp.grupoj.backenddesappapi.repository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>, JpaSpecificationExecutor<Review> {
    List<Review> findAll();



    Optional<Review> findById(Integer integer);

    List<Review> findAllByTitleId(Integer idTitle);

    List<Review> findReviewsByTitleIdAndUser(Integer titleId, Critic user);




}


