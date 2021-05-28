package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LocationRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.SourceRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private SourceRepository sourceRepo;

    @Autowired
    private LocationService locationSrvc;

    @Transactional
    public User getBySourceAndUserIdAndNickId(Integer sourceId, String userId,String userNick, Integer locationId) throws NonExistentSourceException, NonExistentLocationException {

        Location location= locationSrvc.getById(locationId);

        Source source = sourceRepo.getById(sourceId).orElseThrow(() -> new NonExistentSourceException(sourceId));

        User user=repo.findBySourceAndUserIdAndUserNick(source,userId,userNick)
                .orElse(new User(source, userId, userNick, location));
        repo.save(user);
        return user;
    }




}
