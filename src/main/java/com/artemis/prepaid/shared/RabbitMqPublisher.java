package com.artemis.prepaid.shared;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Generic method to send messages of any type
    public <T> void sendMessage(String queueName, T message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
