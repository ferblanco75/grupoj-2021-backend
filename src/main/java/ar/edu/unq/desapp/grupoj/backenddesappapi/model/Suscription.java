package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class Suscription {

    private Source source;
    private Integer titleId;

    public Suscription (Integer aTitleId, Source aSource){
        this.source=aSource;
        this.titleId=aTitleId;
    }
    
}
