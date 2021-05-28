package ar.edu.unq.desapp.grupoj.backenddesappapi;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.exceptions.NonExistentSourceException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BackendDesappApiApplicationTests {

	@Autowired
	private UserService userSrvc;

	@Autowired
	private SourceService sourceSrvc;
	@Autowired
	private DecadeService decadeSrvc;

	@Autowired
	private CriticService criticSrvc;

	@Test
	void ratingThreeInReviewReturnsThree() {
		User user = Mockito.mock(User.class);
		Language lang = new Language("Spanish");
		Review review = new Review(1, user,"Maso, para un domingo zafa",
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

	@Test
	void UserServiceTest1() throws NonExistentLocationException, NonExistentSourceException {
		Source source = Mockito.mock(Source.class);
		Location location = Mockito.mock(Location.class);
		User user = new User(source,"testUser","test1",location);
		User retrievedUser= userSrvc.getBySourceAndUserIdAndNickId(1,"testUser","test1",1);
		assertEquals("testUser", retrievedUser.getUserId());
		assertEquals("test1", retrievedUser.getUserNick());

	}


	@Test
	void GetOneForCriticService() throws NonExistentSourceException {

		Critic retrievedCritic= criticSrvc.getBySourceAndCriticId(1,"ventura");
		assertEquals("Netflix-2", retrievedCritic.getSource().getName());
		assertEquals("ventura", retrievedCritic.getUserId());


	}

	@Test
	void RetrieveOneUserFromService() throws NonExistentLocationException, NonExistentSourceException {

		Source sourceRetrieved = sourceSrvc.getById(1).get();
		assertEquals("Netflix-2", sourceRetrieved.getName());
		assertEquals(1, sourceRetrieved.getId());
	}

	@Test
	void RetrieveCriticWithUnknownSourceFromServiceAndGetSourceException() {
		Exception exception = assertThrows(NonExistentSourceException.class, () -> {
			criticSrvc.getBySourceAndCriticId(999,"pepe");
		});

		String expectedMessage = "There is no source with ID 999";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void RetrieveUserWithUnknownSourceFromServiceAndGetLocationException() {
		Exception exception = assertThrows(NonExistentLocationException.class, () -> {
			userSrvc.getBySourceAndUserIdAndNickId(1,"pepe","pepe",999);
		});

		String expectedMessage = "There is no Location with ID 999";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void RetrieveAllUsersFromService() throws NonExistentLocationException, NonExistentSourceException {

		List<String> sources =new ArrayList<>();

		Iterator<Source> itr = sourceSrvc.findAll().iterator();
		while(itr.hasNext()) {
			Source source = itr.next();
			sources.add(source.getName());
		}

		assertEquals(6, sources.size());
		assertTrue(sources.contains("Netflix"));
		assertTrue(sources.contains("Disney+"));
		assertTrue(sources.contains("Amazon Prime Video"));
		assertTrue(sources.contains("Paramount"));
		assertTrue(sources.contains("HBO Go"));
		assertTrue(sources.contains("Netflix-2"));

	}

	@Test
	void RetrieveOneDecadeFromService() {
		Decade decadeRetrieved = decadeSrvc.getById("D80").get();
		assertEquals(1980, decadeRetrieved.getFrom());
		assertEquals(1989, decadeRetrieved.getTo());
	}

	@Test
	void RetrieveAllDecadeFromService() {

		List<Decade> decades =new ArrayList<>();

		Iterator<Decade> itr = decadeSrvc.findAll().iterator();
		while(itr.hasNext()) {
			Decade decade= itr.next();
			decades.add(decade);
		}

		assertEquals(5, decades.size());


	}




}
