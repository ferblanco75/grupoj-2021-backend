package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Statistics;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.EstatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StatisticsService {

    @Autowired
    private EstatisticsRepository repo;

    @Transactional
    public List<Statistics> getAllStatistics() {
        return repo.findAll();
    }

    @Transactional
    public void update(String methodName){
        Statistics methodData = repo
                .findById(methodName)
                .orElse(new Statistics(methodName));

        methodData.update();
        repo.save(methodData);
    }


}
