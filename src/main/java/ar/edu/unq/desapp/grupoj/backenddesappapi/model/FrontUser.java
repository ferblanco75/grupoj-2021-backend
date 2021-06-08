package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "frontuser",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"})}
)

public class FrontUser  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private boolean active=true;
    private String roles="USER";
    //private Source source;

    protected FrontUser(){}

    public FrontUser(String email, String name, String password){
        this.userName=email;
        this.name=name;
        this.password=password;
    }


    public void setId(Integer id) {
        this.id = id;
    }
//    public void setSource(Source source) { this.source = source;}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return userName;
    }

    public boolean isActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {  return name;   }

}

