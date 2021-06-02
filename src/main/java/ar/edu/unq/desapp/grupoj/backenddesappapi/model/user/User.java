package ar.edu.unq.desapp.grupoj.backenddesappapi.model.user;



import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Source;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.UserDTO;

import javax.persistence.*;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"source", "userId","userNick"})}
)
public class User extends Critic {

    @Column(name="userNick")
    private String userNick;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Location location;

    protected User(){}

    public User (Source source, String userId, String userNick, Location location){
        super(source,userId);
        this.userNick=userNick;
        this.location = location;
    }


    public String getUserNick() {
        return userNick;
    }

    public Location getLocation() {
        return location;
    }

    public Integer getSourceId(){ return super.getSourceId();}

    @Override
    public String getUniqueIdString(){
        return super.getUniqueIdString() + getUserNick();
    }


    public UserDTO toDTO(Integer sourceId, String userId, String userNick, Integer locationId) {
        return new UserDTO(sourceId, userId, userNick, locationId);
    }

}
