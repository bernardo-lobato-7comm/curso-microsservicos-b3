package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import java.util.UUID;

public class OrderCreatedEvent  extends DomainEvent {
    private final UUID orderID;
    private final UUID userID;

    public OrderCreatedEvent(UUID orderID, UUID userID) {
        super();
        this.orderID = orderID;
        this.userID = userID;
    }
}
