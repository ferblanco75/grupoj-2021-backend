package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.CriticRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CriticService {

    @Autowired
    private CriticRepository repo;

    @Autowired
    private SourceRepository sourceRepo;

    @Transactional
    public Critic getBySourceAndCriticId(Integer sourceId, String criticId) throws NonExistentSourceException {
        Source source = sourceRepo.getById(sourceId).orElseThrow(() -> new NonExistentSourceException(sourceId));

        Critic critic=repo.findBySourceAndUserId(source,criticId)
                .orElse(new Critic(source, criticId));
        repo.save(critic);
        return critic;
    }




}
