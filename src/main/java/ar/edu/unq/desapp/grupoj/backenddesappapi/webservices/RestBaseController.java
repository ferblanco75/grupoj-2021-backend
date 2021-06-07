package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@EnableAutoConfiguration
public class RestBaseController {}