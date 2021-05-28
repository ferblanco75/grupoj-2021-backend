package ar.edu.unq.desapp.grupoj.backenddesappapi;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LanguageRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.UserRepository;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.*;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs.RateDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs.UserDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentLanguageException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.Exceptions.NonExistentSourceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BackendDesappApiAuxiliarTests {

	@Autowired
	private LanguageService langSrvc;

	@Autowired
	private LocationService locationSrvc;


	@Test
	void RetrieveAllLanguagesFromService() {
		List<Language> languages = langSrvc.findAll();
		assertEquals(4, languages.size());
		List<String> langValues = languages.stream().map(i->i.getValue()).collect(Collectors.toList());
		assertTrue(langValues.contains("Español"));
		assertTrue(langValues.contains("Frances"));
		assertTrue(langValues.contains("Ingles"));
		assertTrue(langValues.contains("Portugues"));

	}

	@Test
	void RetrieveOneLanguageFromServiceById() throws NonExistentLanguageException {
		Language lang= langSrvc.getById(1);
		assertEquals("Español",lang.getValue());
	}

	@Test
	void RetrieveOneNonExistentLanguageFromServiceById() throws NonExistentLanguageException {
		Exception exception = assertThrows(NonExistentLanguageException.class, () -> {
			langSrvc.getById(999);
		});

		String expectedMessage = "There is no Language with ID 999";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));


	}

	@Test
	void RetrieveAllLocationsFromService() {
		List<Location> locations = locationSrvc.findAll();
		assertEquals(5, locations.size());
		List<String> cities = locations.stream().map(i->i.getCity()).collect(Collectors.toList());
		List<String> countries= locations.stream().map(i->i.getCountry()).collect(Collectors.toList());
		assertTrue(cities.contains("Buenos Aires"));
		assertTrue(cities.contains("Mar del Plata"));
		assertTrue(cities.contains("Rosario"));
		assertTrue(cities.contains("Rio de Janeiro"));
		assertTrue(cities.contains("Valparaiso"));

		assertTrue(countries.contains("Argentina"));
		assertTrue(countries.contains("Brasil"));
		assertTrue(countries.contains("Chile"));


	}



}