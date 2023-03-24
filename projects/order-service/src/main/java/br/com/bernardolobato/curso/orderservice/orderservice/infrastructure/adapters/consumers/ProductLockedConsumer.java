package br.com.bernardolobato.curso.orderservice.orderservice.infrastructure.adapters.consumers;

import br.com.bernardolobato.curso.orderservice.orderservice.application.events.ProductsLockedEvent;
import br.com.bernardolobato.curso.orderservice.orderservice.application.port.inbound.OrderInbound;
import io.confluent.shaded.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductLockedConsumer {
    private final OrderInbound orderInbound;
    @Autowired
    public ProductLockedConsumer(OrderInbound userInbound) {
        this.orderInbound = userInbound;
    }

    @KafkaListener(topics = "ProductsLockedEvent", groupId = "OrderService")
    public void listen(String in) {
        System.out.println(in);
        ProductsLockedEvent e = new Gson().fromJson(in, ProductsLockedEvent.class);
//        this.orderInbound.bookProducts(e.());
    }
}
