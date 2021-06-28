package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.AuthenticationRequest;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.AuthenticationResponse;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.FrontUserService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RegisterDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.UserAlreadyExistsException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@EnableAutoConfiguration

public class FrontUserController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private FrontUserService service;

    @GetMapping("/frontusers")
    public ResponseEntity<List<FrontUser>> getAllFrontUsers() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@Valid @RequestBody RegisterDTO registerRequest) throws UserAlreadyExistsException {
        return new ResponseEntity(
                    service.save(registerRequest.toModel()),
                    HttpStatus.CREATED
                );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );



        final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}