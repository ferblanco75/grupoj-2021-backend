package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.helper.TestService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class BackendDesappApiApplicationTests {

	@Autowired
	public TestService testService;

	@BeforeEach
	void setupInicial(){
		testService.crearDatosIniciales();
	}

	@AfterEach
	void setupFinal(){
		testService.eliminarTodo();
	}

	@Test
	void ratingThreeInReviewReturnsThree() {
		Language lang = new Language("Spanish");
		Review review = new Review(1, "Maso, para un domingo zafa",
				"pochoclera",3,true, lang);
		assertEquals(3, review.getRating());
	}


	@Test
	void getUserCriticNamedJoeReturnsJoe(){
		Source source = new Source("NETFLIX");
		Critic critic = new Critic(source,"Joe");
		assertEquals("Joe", critic.getUserId());
	}


	@Test
	void EspanolLanguageReturnsValueEspanol(){
		Language spanish = new Language("Español");
		assertEquals("Español",spanish.getValue());
	}

	@Test
	void LocationCOuntryItalyReturnsItaly(){
		Location location = new Location("italy","Parma");
		assertEquals("italy",location.getCountry());
	}

	@Test
	void LocationCityParmaReturnsParma(){
		Location location = new Location("italy","Parma");
		assertEquals("Parma",location.getCity());
	}
	@Test
	void RateDtoReviewId123Returns123(){
		UserDTO user = new UserDTO(1,"pepe.test@gmail.com",
				"pepito10",123);
		RateType rateType = RateType.UP;
		RateDTO rateDTO = new RateDTO(user,123, rateType);
		assertEquals(123, rateDTO.reviewId);
	}
	//RateDTO(UserDTO user, Integer reviewId,RateType rateType)
}
