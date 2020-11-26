package app.spring.caminhoneiro.rest.rabbitmq;

import app.spring.caminhoneiro.application.service.CaminhoneiroService;
import app.spring.caminhoneiro.rest.dto.AddFreteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaminhoneiroConsumer {

    @Autowired
    private CaminhoneiroService caminhoneiroService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"frete-queue"}, concurrency = "1")
    public void receivedMessage(String message) throws JsonProcessingException {
        AddFreteDTO dto = objectMapper.readValue(message, AddFreteDTO.class);

        caminhoneiroService.addFrete(dto);
    }

}
