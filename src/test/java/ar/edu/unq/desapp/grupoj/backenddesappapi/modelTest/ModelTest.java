package ar.edu.unq.desapp.grupoj.backenddesappapi.modelTest;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.RateType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewRate;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ModelTest {


    @Test
    public void prueba(){
        User user = Mockito.mock(User.class);
        Review review = Mockito.mock(Review.class);
        ReviewRate rate = new ReviewRate(RateType.UP,user,review);
        assertEquals(null,rate.getId());
        assertEquals(review,rate.getReview());
        assertEquals(RateType.UP,rate.getType());
        assertEquals(user, rate.getUser());
    }

}
