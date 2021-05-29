package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "frontusers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)

public class FrontUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    public FrontUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    protected FrontUser(){}

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
