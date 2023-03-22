package br.com.bernardolobato.curso.orderservice.orderservice.infrastructure.adapters.consumers;

import br.com.bernardolobato.curso.orderservice.orderservice.application.events.OrderValidateUserEvent;
import br.com.bernardolobato.curso.orderservice.orderservice.application.port.inbound.OrderInbound;
import io.confluent.shaded.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class UserValidatedConsumer {
    private final OrderInbound orderInbound;
    @Autowired
    public UserValidatedConsumer(OrderInbound userInbound) {
        this.orderInbound = userInbound;
    }

    @KafkaListener(topics = "UserValidatedEvent", groupId = "OrderService")
    public void listen(String in) {
        System.out.println(in);
        OrderValidateUserEvent e = new Gson().fromJson(in, OrderValidateUserEvent.class);
        this.orderInbound.bookProducts(e.getOrderId());
    }
}
