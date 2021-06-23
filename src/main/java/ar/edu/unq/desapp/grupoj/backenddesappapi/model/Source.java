package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.config.MessagingConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Component
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    protected Source(){}

    public Source(String platformName){
        this.name=platformName;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(ReviewNotification reviewNotification) {
        // System.out.println("Message recieved from queue : " + reviewNotification);
        suscribe();
    }


    public Suscription suscribe(){
        String url = "/api/character/random";
        //problema 1 el id que levanto aca no es del title, es el id del Source que se invoque
        //problema 2 no me guarda una suscripcion en la base
        //problema 3 como hago para hacer un get a esta url? vamos a armar la estructura controller-service-repo??
        Suscription aSus = new Suscription(this.id, url );
        return  aSus;
    }

}
