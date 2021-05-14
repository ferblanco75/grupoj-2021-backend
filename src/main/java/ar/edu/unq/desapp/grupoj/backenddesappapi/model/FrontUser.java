package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


import javax.persistence.*;

@Entity
@Table(
        name = "frontusers",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)

public class FrontUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public String name;
    public String email;
    public String password;

    public FrontUser(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    protected FrontUser(){}
}
