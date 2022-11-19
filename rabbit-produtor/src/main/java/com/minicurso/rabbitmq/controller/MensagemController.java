package com.minicurso.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.minicurso.rabbitmq.DTO.Mensagem;
import com.minicurso.rabbitmq.service.RabbitService;

@RestController
public class MensagemController {

    // Injeção responsável por se comunicar com o nosso serviço
    @Autowired
    private RabbitService service;

    @PostMapping("/enviar")
    public ResponseEntity<?> enviar(@RequestBody Mensagem msg) {

        // Enviando para a fila do RabbitMq
        this.service.enviarMensagem("TESTE", msg);

        System.out.println("---------------------");
        System.out.println("| Mensagem enviada! |");
        System.out.println("---------------------");

        // Respondendo a requisição OK
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
