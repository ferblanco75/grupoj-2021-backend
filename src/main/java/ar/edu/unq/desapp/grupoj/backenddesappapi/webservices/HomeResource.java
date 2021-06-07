package ar.edu.unq.desapp.grupoj.backenddesappapi.webservices;

import org.springframework.web.bind.annotation.GetMapping;


public class HomeResource extends RestBaseController{


    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}
