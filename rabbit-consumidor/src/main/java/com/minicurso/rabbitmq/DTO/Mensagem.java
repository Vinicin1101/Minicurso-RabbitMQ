package com.minicurso.rabbitmq.DTO;

import java.io.Serializable;

//  A classe serializable é usada para transformar um objeto em uma sequência de bytes, possibilitando a transferencia de informações (tipo o json)
public class Mensagem implements Serializable {
    public String conteudo;
    public String remetente;
}
