package com.minicurso.rabbitmq.consumidor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.minicurso.rabbitmq.DTO.Mensagem;

@Component
public class RabbitConsumer {

    @RabbitListener(queues = "TESTE") // <- Ouvinte da fila
    private void consumidor(Mensagem msg) {
        System.out.println("------------- Mensagem recebida -------------");
        System.out.println("@" + msg.remetente + "> " + msg.conteudo);
        System.out.println("---------------------------------------------");
    }
}
