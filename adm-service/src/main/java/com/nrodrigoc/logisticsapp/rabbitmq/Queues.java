package com.nrodrigoc.logisticsapp.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Queues {

    @Bean
    public Queue mailQueue() {
        return new Queue("mail-queue", true);
    }

    @Bean
    public Queue freteQueue() { return new Queue("frete-queue", true); }

    @Bean
    public Queue pedidoQueue() { return new Queue("pedido-queue", true); }
}
