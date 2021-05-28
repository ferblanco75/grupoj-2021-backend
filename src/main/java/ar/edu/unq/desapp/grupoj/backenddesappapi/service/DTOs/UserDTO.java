package ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs;

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
