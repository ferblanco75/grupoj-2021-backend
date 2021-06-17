package ar.edu.unq.desapp.grupoj.backenddesappapi.publisher;

import ar.edu.unq.desapp.grupoj.backenddesappapi.config.MessagingConfig;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.Review;
import ar.edu.unq.desapp.grupoj.backenddesappapi.model.ReviewNotification;
import ar.edu.unq.desapp.grupoj.backenddesappapi.service.dtos.ReviewDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class ReviewPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{sourceName}")
    public String notifyReview(@RequestBody ReviewDTO review, @PathVariable String sourceName) {
        //review.setTitleId(194324);
        //restaurantservice
        //payment service
        ReviewNotification notification = new ReviewNotification(review, "new review for " + sourceName);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, notification);
        return "Success !!";
    }
}