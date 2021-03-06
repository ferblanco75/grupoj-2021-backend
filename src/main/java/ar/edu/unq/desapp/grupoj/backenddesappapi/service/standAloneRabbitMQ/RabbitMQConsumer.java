package ar.edu.unq.desapp.grupoj.backenddesappapi.service.standAloneRabbitMQ;

import ar.edu.unq.desapp.grupoj.backenddesappapi.config.MessagingConfig;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SuscriptionService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.SuscriptionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class RabbitMQConsumer {

    static Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private SuscriptionService service;

    @Autowired
    private SuscriptionController suscriptionController;

    protected RabbitMQConsumer(){}


    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Integer titleId) {
        service.getAllByTitleId(titleId).forEach(suscription -> this.callUrl(suscription.getUrl()));

    }

    private void callUrl(String url) {
        logger.info("======mensaje enviado a url " + url);
        try {
            URL target = new URL(url);
            HttpURLConnection con = (HttpURLConnection) target.openConnection();
            con.setRequestMethod("GET");
            con.getInputStream();
        }catch(Exception e){
            //Avoiding any exceptions
        }
    }





}
