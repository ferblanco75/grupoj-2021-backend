package ar.edu.unq.desapp.grupoj.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class BackendDesappApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendDesappApiApplication.class, args);
	}

}
