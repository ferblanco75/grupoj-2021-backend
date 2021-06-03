package ar.edu.unq.desapp.grupoj.backenddesappapi;


import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;

import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SourceService;

import ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.SourceController;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SourceControllerTests {

    @Mock
    SourceService service ;

    @InjectMocks
    SourceController controller;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void SourceControllerTest() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        List<Source> list= new ArrayList<>();
        Source source= new Source("NetFlix");
        list.add(source);
        when(service.findAll()).thenReturn(list);

        MockHttpServletResponse response= mvc.perform(get("/sources")).andExpect(status().isOk()).andReturn().getResponse();

        assertEquals("[{\"id\":null,\"name\":\"NetFlix\"}]",response.getContentAsString());
    }

}
