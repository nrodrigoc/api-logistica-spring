package com.nrodrigoc.logisticsapp.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrodrigoc.logisticsapp.service.CaminhoneiroFreteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

    @Autowired
    private CaminhoneiroFreteService caminhoneiroFreteService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = {"pedido-queue"}, concurrency = "1")
    public void receivedMessage(Integer id_frete) {

        caminhoneiroFreteService.entregaFrete(id_frete);

    }

}
