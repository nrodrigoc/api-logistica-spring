package app.spring.caminhoneiro.rest.rabbitmq;

import app.spring.caminhoneiro.application.service.MailService;
import app.spring.caminhoneiro.rest.dto.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {

    @Autowired
    private MailService mailService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"mail-queue"}, concurrency = "1")
    public void receivedMessage(String message) throws JsonProcessingException {
        EmailDTO dto = objectMapper.readValue(message, EmailDTO.class);

        mailService.sendSimpleMail(dto);
    }

}
