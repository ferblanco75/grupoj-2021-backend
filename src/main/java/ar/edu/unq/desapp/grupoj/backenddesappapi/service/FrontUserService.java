package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.MyUserDetails;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.FrontUserRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FrontUserService implements UserDetailsService {

    @Autowired
    private FrontUserRepository frontUserRepo;

    public List<FrontUser> findAll() {
        return frontUserRepo.findAll();
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        frontUserRepo.save(new FrontUser("alonso.em@gmail.com","Enrique Alonso","123456"));
    }

    @Transactional
    public FrontUser save(FrontUser frontuser) throws UserAlreadyExistsException {
        try {
            frontUserRepo.save(frontuser);
            return frontuser;
        }catch(DuplicateKeyException e){
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<FrontUser> frontUser = frontUserRepo.findByUserName(username);
        frontUser.orElseThrow(() ->  new UsernameNotFoundException("Not Found: " + username));
        return frontUser.map(MyUserDetails::new).get();
    }
}
