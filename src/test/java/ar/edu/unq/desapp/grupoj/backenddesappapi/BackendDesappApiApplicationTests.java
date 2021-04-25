package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BackendDesappApiApplicationTests {

	@Test
	void ratingSevenInReviewReturnsSeven() {
		Review review = new Review("buenaza",7);
		assertEquals(7, review.getRating());
	}



}
