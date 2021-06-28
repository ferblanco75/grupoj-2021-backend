package ar.edu.unq.desapp.grupoj.backenddesappapi.service.standAloneRabbitMQ;

import ar.edu.unq.desapp.grupoj.backenddesappapi.config.MessagingConfig;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.SuscriptionService;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewPremiumDTO;
import ar.edu.unq.desapp.grupoj.backenddesappapi.webservices.SuscriptionController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
public class RabbitMQConsumer {

    @Autowired
    private SuscriptionService service;

    @Autowired
    private SuscriptionController suscriptionController;

    protected RabbitMQConsumer(){}


    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Integer titleId) {
        //Aca agarro todos las suscripciones con titleId y les hago una llamada a las url de esas suscripciones con getURL
        service.getAllByTitleId(titleId).forEach(suscription -> suscriptionController.callUrl(suscription.getUrl()));
    }




}
