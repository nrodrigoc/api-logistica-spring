package app.spring.caminhoneiro.application.service.impl;

import app.spring.caminhoneiro.application.service.MailService;
import app.spring.caminhoneiro.rest.dto.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    private final RabbitTemplate rabbitTemplate;

    private final String QUEUE_NAME;

    private final ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    public MailServiceImpl(RabbitTemplate rt) {
        this.rabbitTemplate = rt;
        QUEUE_NAME = "mail-queue";
        objectMapper = new ObjectMapper();
    }

    @Override
    public void sendSimpleMail(EmailDTO dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getTo());
        message.setSubject(dto.getSubject());
        message.setText(dto.getContent());
        message.setFrom(from);

        mailSender.send(message);
    }

    @Override
    public void publishMessage(EmailDTO dto) {
        String serializedObj = "";

        try {
            serializedObj = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        logger.info("Sending message to RabbitMQ");
        rabbitTemplate.convertAndSend("", QUEUE_NAME, serializedObj);
        logger.info("Message published!");
    }
}
