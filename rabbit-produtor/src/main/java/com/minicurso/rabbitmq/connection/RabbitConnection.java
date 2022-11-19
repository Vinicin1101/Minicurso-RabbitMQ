package com.minicurso.rabbitmq.connection;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component // <- Indica que o Spring precisa instanciar ao iniciar
public class RabbitConnection {

    AmqpAdmin amqpAdmin;
    private static String NOME_EXCHANGE = "amq.direct";

    // Injeção de dependência AMQP
    public RabbitConnection(AmqpAdmin admin) {
        this.amqpAdmin = admin;
    }

    // Método de criação de um fila
    private Queue fila(String nomeFila) {

        // Retornando uma fila
        return new Queue(nomeFila, true);
    }

    // Método de criação de uma exchange
    private DirectExchange exchangeDireta() {

        // Retornando a exchange
        return new DirectExchange(NOME_EXCHANGE);
    }

    // Método de ligação de uma fila
    private Binding ligar(Queue fila, DirectExchange exchange) {

        // Definindo os parâmetros
        String destino = fila.getName();
        String rota = fila.getName(); // <- RouterKey usado para identificar a fila
        String ex = exchange.getName();

        // Retornando a Bind
        return new Binding(destino, Binding.DestinationType.QUEUE, ex, rota, null);
    }

    @PostConstruct // Indica ao Spring que o método deve ser executado quando a classe for
                   // construida
    private void addFila() {

        // Instanciando as filas
        Queue novaFila = this.fila("TESTE");
        Queue outraFila = this.fila("DEMO");

        // Instanciando a exchange
        DirectExchange ex = this.exchangeDireta();

        // Binding (ligação)
        Binding ligacaoNovaFila = this.ligar(novaFila, ex);
        Binding ligacaoOutraFila = this.ligar(outraFila, ex);

        // Adicionando as filas ao RabbitMQ
        this.amqpAdmin.declareQueue(novaFila);
        this.amqpAdmin.declareQueue(outraFila);

        // Adicionando a Exchange (Por padrão já existe, então não é necessário)
        this.amqpAdmin.declareExchange(ex);

        // Adicionando as bindings
        this.amqpAdmin.declareBinding(ligacaoNovaFila);
        this.amqpAdmin.declareBinding(ligacaoOutraFila);
    }
}
