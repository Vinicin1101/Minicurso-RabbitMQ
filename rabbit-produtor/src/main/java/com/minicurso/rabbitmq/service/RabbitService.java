package com.minicurso.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    // Injeção de dependência para se comunicar com o RabbitMQ
    @Autowired
    private RabbitTemplate template;

    // Método para enviar a mensagem
    public void enviarMensagem(String routerKey, Object mensagem) {
        this.template.convertAndSend(routerKey, mensagem);
    }
}
