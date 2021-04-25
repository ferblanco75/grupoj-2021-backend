package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Language;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BackendDesappApiApplicationTests {

	@Test
	void ratingSevenInReviewReturnsSeven() {
		Review review = new Review(1, new Source("Netflix"),"Maso, para un domingo zafa",
				"pochoclera",3,true,"fernando.test@gmail.com","fer",
				new Location("Ecuador","Quito"),new Language("Spanish"));
		assertEquals(3, review.getRating());
	}



}
