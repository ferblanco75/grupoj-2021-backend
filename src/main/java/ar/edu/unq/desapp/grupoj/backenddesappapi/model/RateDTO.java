package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class RateDTO {

    public UserDTO user;
    public Integer reviewId;
    public RateType rateType;

    public RateDTO(UserDTO user, Integer reviewId,RateType rateType){
        this.user=user;
        this.reviewId=reviewId;
        this.rateType=rateType;
    }
    protected  RateDTO(){}

}
