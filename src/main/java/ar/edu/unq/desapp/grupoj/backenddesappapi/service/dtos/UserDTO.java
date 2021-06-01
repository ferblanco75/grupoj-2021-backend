package ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.Critic;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.user.User;

public class UserDTO {

    private Integer sourceId;
    private String userId;
    private String userNick;
    private Integer locationId;

    protected UserDTO(){}

    public String getUserId() {
        return userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public UserDTO(Integer sourceId, String userId, String userNick, Integer locationId){
        this.sourceId=sourceId;
        this.userId=userId;
        this.userNick=userNick;
        this.locationId=locationId;
    }

    public static UserDTO fromModel(User user){
        return new UserDTO(user.getSource().getId(),user.getUserId(),user.getUserNick(),user.getLocation().getId());
    }


}
