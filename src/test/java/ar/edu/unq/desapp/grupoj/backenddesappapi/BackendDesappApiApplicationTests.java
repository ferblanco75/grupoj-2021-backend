package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BackendDesappApiApplicationTests {

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



}
