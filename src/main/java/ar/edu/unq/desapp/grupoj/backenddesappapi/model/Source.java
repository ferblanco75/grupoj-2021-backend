package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

import ar.edu.unq.desapp.grupoj.backenddesappapi.config.MessagingConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
        System.out.println("Message recieved from queue : " + reviewNotification);
    }

}
