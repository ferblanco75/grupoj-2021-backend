package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class SuscriptionTest {

    @Test
    public void suscription(){
        Source source = Mockito.mock(Source.class);
        Integer titleId= 10;
        Suscription suscription = new Suscription(titleId,source);

        assertEquals(source,suscription.getSource());
        assertEquals(titleId,suscription.getTitleId());
    }
}
