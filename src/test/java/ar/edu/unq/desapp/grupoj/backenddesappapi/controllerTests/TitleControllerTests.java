package ar.edu.unq.desapp.grupoj.backenddesappapi.controllerTests;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Decade;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.RateType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Genre;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.Title;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.titles.TitleType;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.LocationService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.TitleService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.InverseReq;
import ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.LocationController;
import ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.TitleController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class TitleControllerTests {

    @Mock
    TitleService service ;

    @InjectMocks
    TitleController controller;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void tryingGetOnControllerTitleGetAllTitles() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();

        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.DRAMA);

        List<Title> list= new ArrayList<>();
        Title title= new Title(9, TitleType.MOVIE,"Die Hard",false,1985,1985,90,genres);
        list.add(title);
        Title title2= new Title(2, TitleType.SHORT,"SNOOPY",false,1978,1979,30,genres);
        list.add(title2);


        when(service.findAll()).thenReturn(list);

        MockHttpServletResponse response= mvc.perform(get("/title")).andExpect(status().isOk()).andReturn().getResponse();

        String expectedResponse="[{\"titleId\":9,\"title\":\"Die Hard\",\"duration\":90,\"type\":\"MOVIE\",\"startYear\":1985,\"endYear\":1985,\"adult\":false},{\"titleId\":2,\"title\":\"SNOOPY\",\"duration\":30,\"type\":\"SHORT\",\"startYear\":1978,\"endYear\":1979,\"adult\":false}]";
        assertEquals(expectedResponse,response.getContentAsString());
    }

    @Test
    public void tryingGetOnControllerJustOneTitleAndGetThatTitle() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();

        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.DRAMA);

        Title title2= new Title(2, TitleType.SHORT,"SNOOPY",false,1978,1979,30,genres);

        when(service.getByTitleId(any())).thenReturn(title2);

        MockHttpServletResponse response= mvc.perform(get("/title/2")).andExpect(status().isOk()).andReturn().getResponse();

        String expectedResponse="{\"titleId\":2,\"title\":\"SNOOPY\",\"duration\":30,\"type\":\"SHORT\",\"startYear\":1978,\"endYear\":1979,\"adult\":false}";
        assertEquals(expectedResponse,response.getContentAsString());
    }

    @Test
    public void tryingGetOnControllerInverseQueryAndGetThoseTitles() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();

        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.DRAMA);

        List<Title> list= new ArrayList<>();
        Title title= new Title(9, TitleType.MOVIE,"Die Hard",false,1985,1985,90,genres);
        list.add(title);
        Title title2= new Title(2, TitleType.SHORT,"SNOOPY",false,1978,1979,30,genres);
        list.add(title2);

        when(service.inverseQuery(any(InverseReq.class))).thenReturn(list);


        InverseReq inverseRequest = new InverseReq();
        inverseRequest.genres=genres;
        List<String> decades = new ArrayList<>();
        decades.add("D80");

        List<String> actors = new ArrayList<>();
        actors.add("Bruce Willis");

        inverseRequest.decade=decades;
        inverseRequest.rating= RateType.UP;
        inverseRequest.minStars=2;
        inverseRequest.actors=actors;


        MockHttpServletResponse response= mvc.perform(post("/title/inverse")
                .content(asJsonString(inverseRequest))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse();

        String expectedResponse="[{\"titleId\":9,\"title\":\"Die Hard\",\"duration\":90,\"type\":\"MOVIE\",\"startYear\":1985,\"endYear\":1985,\"adult\":false},{\"titleId\":2,\"title\":\"SNOOPY\",\"duration\":30,\"type\":\"SHORT\",\"startYear\":1978,\"endYear\":1979,\"adult\":false}]";
        assertEquals(expectedResponse,response.getContentAsString());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
