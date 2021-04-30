package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BackendDesappApiApplicationTests {

	@Test
	void ratingSevenInReviewReturnsSeven() {
		User user = new User("fernando.test@gmail.com",
							"fer",
							new Language("Spanish"),
							new Location("Ecuador","Quito"));
		Review review = new Review(1, new Source("Netflix"),"Maso, para un domingo zafa",
				"pochoclera",3,true);
		assertEquals(3, review.getRating());
	}



}
