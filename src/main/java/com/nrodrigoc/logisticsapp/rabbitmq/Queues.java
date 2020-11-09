package com.nrodrigoc.logisticsapp.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class Queues {

    @Bean
    public Queue mailQueue() {
        return new Queue("mail-queue", true);
    }

}
