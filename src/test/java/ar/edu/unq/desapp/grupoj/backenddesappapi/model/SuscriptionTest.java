package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SuscriptionTest {

    @Test
    public void suscription(){
        String url = "/api/character/random";
        Integer titleId= 10;
        Suscription suscription = new Suscription(titleId,url);

        assertEquals(url,suscription.getUrl());
        assertEquals(titleId,suscription.getTitleId());
    }
}
