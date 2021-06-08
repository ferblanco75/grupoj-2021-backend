package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.FrontUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@CrossOrigin
//@RestController
@Service
public class FrontUserService implements UserDetailsService {

    @Autowired
    private FrontUserRepository frontUserRepo;

    public List<FrontUser> findAll() {
        return frontUserRepo.findAll();
    }

    @Transactional
    public FrontUser save(FrontUser frontuser) {
        frontUserRepo.save(frontuser);
        return frontuser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<FrontUser> frontUser = frontUserRepo.findByUserName(username);
        frontUser.orElseThrow(() ->  new UsernameNotFoundException("Not Found: " + username));
        return frontUser.map(MyUserDetails::new).get();
    }
}
