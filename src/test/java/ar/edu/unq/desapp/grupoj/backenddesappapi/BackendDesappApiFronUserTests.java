package ar.edu.unq.desapp.grupoj.backenddesappapi;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.FrontUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class BackendDesappApiFronUserTests {

        @Autowired
        private FrontUserService userService;

        @Test
        void retrieveAllUsersAndGetNone() {
            List<FrontUser> users = userService.findAll();
            assertFalse(users.isEmpty());
        }

        @Test
        void retrieveAllUsersAndGetOne() {
            FrontUser user = new FrontUser("UNQUIFLIX","alonso.em@gmail.com","123456");
            userService.save(user);
            List<FrontUser> users = userService.findAll();
            assertEquals(2,users.size());
        }



    }

