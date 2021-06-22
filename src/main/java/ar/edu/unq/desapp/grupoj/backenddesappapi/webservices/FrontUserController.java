package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.AuthenticationRequest;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.AuthenticationResponse;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.FrontUserService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RegisterDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<FrontUser> saveUser(@RequestBody RegisterDTO registerReq) {
        return new ResponseEntity(
                    service.save(registerReq.toModel()),
                    HttpStatus.CREATED
                );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}