package ar.edu.unq.desapp.grupoj.backenddesappapi.repository.titlesRepository;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends CrudRepository<Title, Integer> {
    Iterable<Title> findAll();
    Optional<Title> getByTitleId(Integer id);

    @Query("select t from Title t JOIN FETCH t.reviews as r group by t having r.rating>=3")

    List<Title> inverseQuery();
            /*{
        String campaignToLaunch = "select pc.* from PushCampaign pc ..."
        Class className = Class.forName("Title.class");
        List<PushCampaign> result = JPA.em()
                .createNativeQuery(campaignToLaunch,className)
                .getResultList();
    }*/


}


