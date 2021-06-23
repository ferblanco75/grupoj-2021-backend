package ar.edu.unq.desapp.grupoj.backenddesappapi.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Suscription {

    //private Source source;
    @Id
    private Integer titleId;
    private String url;

    public Suscription (Integer aTitleId, String url){
        //this.source=aSource;
        this.titleId=aTitleId;
        this.url = url;
    }



    public Integer getTitleId() {
        return titleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
