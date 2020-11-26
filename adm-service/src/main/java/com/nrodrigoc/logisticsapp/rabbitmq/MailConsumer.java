package com.nrodrigoc.logisticsapp.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrodrigoc.logisticsapp.email.service.MailService;
import com.nrodrigoc.logisticsapp.rest.dto.EmailDTO;
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
