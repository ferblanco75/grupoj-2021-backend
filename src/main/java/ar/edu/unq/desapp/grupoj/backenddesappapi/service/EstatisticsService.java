package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Estatistics;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.EstatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EstatisticsService {

    @Autowired
    private EstatisticsRepository repo;

    @Transactional
    public List<Estatistics> getAllEstatistics() {
        return repo.findAll();
    }

    @Transactional
    public void update(String methodName){
        Estatistics methodData = repo
                .findById(methodName)
                .orElse(new Estatistics(methodName));

        methodData.update();
        repo.save(methodData);
    }


}
