package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estatistics {

    @Id
    String id="";

    Integer invocations=0;

    protected Estatistics(){}

    public Estatistics(String methodName){
        this.id=methodName;
    }

    public void update(){
        this.invocations=this.invocations+1;
    }

    public Integer getInvocations() {
        return invocations;
    }

    public String getId() {
        return id;
    }
}
