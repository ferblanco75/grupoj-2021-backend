package ar.edu.unq.desapp.grupoj.backenddesappapi.repository.TitlesRepository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Cast.Cast;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Cast.Person;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Decade;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Genre;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.InverseReq;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class TitleRepositoryQueries {

    @PersistenceContext
    EntityManager em;


    public List<Title> inverseQuery(InverseReq req, List<Decade> decades)
    {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Title> cq = cb.createQuery(Title.class);

//        req.actors


        Root<Title> title = cq.from(Title.class);
        Join<Title,Review> joinTitleReviews= title.join("reviews");



        //Filter by conjunction of isMember over genres
        Expression<List<Genre>> genreProperty = title.get("genres");
        Predicate predicateConjunction = cb.conjunction();
        for(Genre oneGenre: req.genres){
            predicateConjunction = cb.and(predicateConjunction, cb.isMember(oneGenre, genreProperty));
        }

        //Filter by conjunction of isMember over Decades
        Expression<Integer> startYear= title.get("startYear");
        Expression<Integer> endYear= title.get("endYear");
        Predicate predicateOr= cb.disjunction();


        for(Decade oneDecade: decades){
            predicateOr= cb.or(predicateOr, cb.and(cb.greaterThanOrEqualTo(startYear,oneDecade.getFrom()),
                                                    cb.lessThanOrEqualTo(startYear,oneDecade.getTo())));

            predicateOr= cb.or(predicateOr, cb.and(cb.greaterThanOrEqualTo(endYear,oneDecade.getFrom()),
                                                    cb.lessThanOrEqualTo(endYear,oneDecade.getTo())));

        }


        //SubQuery para filtrar los titulos que no tienen a los actores que se solicitan
        Subquery<Long> subquery3 = cq.subquery(Long.class);
        Root<Title> subRootTitle = subquery3.from(Title.class);
        Join<Title, Cast> joinCast= subRootTitle.join("cast");
        Join<Cast, Person> joinPerson= joinCast.join("person");

        Path<Long> idPathToTitle = subRootTitle.get("titleId");
        Expression<String> nameProp = joinPerson.get("name");

        Predicate predicateD = cb.disjunction();
        for(String oneName: req.actors){
            predicateD= cb.or(predicateD, cb.like(nameProp,oneName));
        }

        Expression<Long> idCount = cb.count(joinPerson.get("name"));
        subquery3.select(idPathToTitle)
                .where(predicateD)
                .groupBy(idPathToTitle)
                .having(cb.gt(idCount, 0));


        //SubQuery para filtrar los titulos tiene reviews con menos estrellas de las deseadas
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Title> subRoot = subquery.from(Title.class);
        Join<Review, Title> join = subRoot.join("reviews");
        Path<Long> idPath = join.get("titleId");
        Expression<Long> idCountExp = cb.count(join.get("id"));
        subquery.select(idPath)
                .where(cb.lt(join.get("rating"),req.minStars))
                .groupBy(idPath)
                .having(cb.gt(idCountExp, 0));

        Subquery<Long> subquery2 = cq.subquery(Long.class);
        Root<Title> subRoot2 = subquery2.from(Title.class);
        Join<Review, Title> join2 = subRoot2.join("reviews");
        Path<Long> idPath2 = join2.get("titleId");
        Expression<Long> rateDiff = cb.diff(join2.get("ratedUp"),join2.get("ratedDown"));
        Predicate predicate = cb.lessThan(rateDiff,(long) 0);
        subquery2.select(idPath2)
                .where(predicate)
                .groupBy(idPath2);

        cq.where(predicateOr,
                predicateConjunction,
                cb.not(title.get("titleId").in(subquery)),
                cb.not(title.get("titleId").in(subquery2)),
                title.get("titleId").in(subquery3)
        );
        TypedQuery<Title> query = em.createQuery(cq);
        return query.getResultList();

    }


}


