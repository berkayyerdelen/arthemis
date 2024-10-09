package com.artemis.prepaid.users.consumers;

import com.artemis.prepaid.users.models.EndUser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EndUserCreatedEventHandler {
    @RabbitListener(queues = "endUser-created-queue")
    public void receiveMessage(EndUser message) {
        System.out.println("Received: " + message.getName());
    }
}
