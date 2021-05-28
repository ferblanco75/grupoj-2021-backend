package ar.edu.unq.desapp.grupoj.backenddesappapi.service.DTOs;

import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Reason;

public class ReportDTO {

    public Integer reviewId;
    public Reason reason;
    public String description;
    public UserDTO user;

    protected ReportDTO(){}

    public ReportDTO(Integer reviewId, Reason reason, String description, UserDTO user){
        this.description=description;
        this.reason=reason;
        this.reviewId=reviewId;
        this.user=user;
    }


}
