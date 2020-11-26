package app.spring.caminhoneiro.rest.rabbitmq;

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
    public Queue caminhoneiroQueue() { return new Queue("frete-queue", true); }
}
