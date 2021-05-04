package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BackendDesappApiApplicationTests {

	public LanguageRepository repository;
	public LanguageService languageService = new LanguageService(repository);

	@Test
	void ratingThreeInReviewReturnsThree() {
		User user = new User("fernando.test@gmail.com",
							"fer",
							new Location("Ecuador","Quito"));
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
	void addingAReviewToAnEmptyReviewListReturnsASizeOneList(){
		Language lang = new Language("French");
		Review review2 = new Review(4, new Source("YouTube"),"excelente",
				"buena peli",3,false, lang);
		List<Review> reviews = new ArrayList<Review>();
		Critic critic = new Critic("Pepe");
		critic.addReview(review2);
		assertEquals(1,reviews.size());
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
