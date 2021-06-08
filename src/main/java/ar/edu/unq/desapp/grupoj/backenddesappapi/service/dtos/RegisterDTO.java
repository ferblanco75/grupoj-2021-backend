package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.FrontUser;

public class RegisterDTO {
    private String email;
    private String password;
    private String name;
    private Integer sourceId;

    public RegisterDTO(Integer source,String email, String name, String password) {
        this.password = password;
        this.email=email;
        this.name=name;
        this.sourceId = source;

    }

    public RegisterDTO() { }

    public FrontUser toModel(){
        return new FrontUser(email,name, password);
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
