package ar.edu.unq.desapp.grupoj.backenddesappapi.service;

import ar.edu.unq.desapp.grupoj.backenddesappapi.exception.NonExistentLocationException;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Location;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LocationRepository;

public class UserDTO {

    public Integer sourceId;
    public String userId;
    public  String userNick;
    public Integer locationId;


    protected UserDTO(){}

    public UserDTO(Integer sourceId,String userId, String userNick, Integer locationId){
        this.sourceId=sourceId;
        this.userId=userId;
        this.userNick=userNick;
        this.locationId=locationId;
    }


}
