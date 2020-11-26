package com.nrodrigoc.logisticsapp.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrodrigoc.logisticsapp.rest.dto.AddFreteDTO;
import com.nrodrigoc.logisticsapp.service.CaminhoneiroService;
import com.nrodrigoc.logisticsapp.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @Autowired
    private CaminhoneiroService caminhoneiroService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"pedido-queue"}, concurrency = "1")
    public void receivedMessage(Integer id_frete) {

        caminhoneiroService.entregaFrete(id_frete);

    }

}
