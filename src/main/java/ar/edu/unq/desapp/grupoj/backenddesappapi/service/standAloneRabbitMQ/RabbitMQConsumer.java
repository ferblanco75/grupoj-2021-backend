package ar.edu.unq.desapp.grupoj.backenddesappapi.service.standAloneRabbitMQ;

import ar.edu.unq.desapp.grupoj.backenddesappapi.config.MessagingConfig;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SuscriptionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    private SuscriptionService service;

    protected RabbitMQConsumer(){}


    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Integer titleId) {
        // System.out.println("Message recieved from queue : " + reviewNotification);
        //suscribe();

        //Aca agarro todos las suscripciones con titleId y les hago una llamada a las url con getURL
        service.getAllByTitleId(titleId).forEach(suscription -> callUrl(suscription.getUrl()));
    }

    private void callUrl(String url){
        //Aca agarras la url y la llamamos por get.
        System.out.println("LLAMANDO A URL " + url);

        //Quitar el system out!

    }

/*
    private Suscription suscribe(){
        String url = "/api/character/random";
        //problema 1 el id que levanto aca no es del title, es el id del Source que se invoque
        //problema 2 no me guarda una suscripcion en la base
        //problema 3 como hago para hacer un get a esta url? vamos a armar la estructura controller-service-repo??
        //Suscription aSus = new Suscription(this.id, url );
        return  aSus;
    }
*/
}
