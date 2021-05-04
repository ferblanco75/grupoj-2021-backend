package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendDesappApiApplicationTests {

	public LanguageRepository repository;


	@Test
	void ratingThreeInReviewReturnsThree() {
		Language lang = new Language("Spanish");
		Review review = new Review(1, new Source("Netflix"),"Maso, para un domingo zafa",
				"pochoclera",3,true, lang);
		assertEquals(3, review.getRating());
	}

	@Test
	void getUserCriticNamedJoeReturnsJoe(){
		Critic critic = new Critic("Joe");
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
		UserDTO user = new UserDTO("pepe.test@gmail.com",
				"pepito10",123);
		RateType rateType = RateType.UP;
		RateDTO rateDTO = new RateDTO(user,123, rateType);
		assertEquals(123, rateDTO.reviewId);
	}
	//RateDTO(UserDTO user, Integer reviewId,RateType rateType)
}
