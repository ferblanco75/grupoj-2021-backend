package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.repository.LocationRepository;

public class UserDTO {

    public String userId;
    public  String userNick;
    public Integer locationId;

    protected UserDTO(){}

    public UserDTO(String userId, String userNick, Integer locationId){
        this.userId=userId;
        this.userNick=userNick;
        this.locationId=locationId;
    }

    public User toModel(LocationRepository locationRepo) throws NonExistentLocationException {
        Location location=locationRepo.findById(locationId).orElseThrow(() -> new NonExistentLocationException(locationId));
        return new User(userId,userNick,location);
    }
}
