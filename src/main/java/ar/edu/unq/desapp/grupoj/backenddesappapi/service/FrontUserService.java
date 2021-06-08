package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.MyUserDetails;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.FrontUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
