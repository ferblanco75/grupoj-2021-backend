package ar.edu.unq.desapp.grupoj.backenddesappapi.model.Cast;

import javax.persistence.*;

@Entity
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;


    @Enumerated(EnumType.STRING)
    private Job job;

    protected Cast(){}

    public Cast(Person person,Job job){
        this.person=person;
        this.job=job;
    }
}
