package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


import javax.persistence.*;

@Entity
@Table(
        name = "frontusers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email","password"})}
)

public class FrontUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;

    public FrontUser(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
