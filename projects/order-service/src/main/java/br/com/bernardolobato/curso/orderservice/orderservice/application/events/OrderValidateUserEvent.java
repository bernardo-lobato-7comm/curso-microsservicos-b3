package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderValidateUserEvent extends DomainEvent {

    private final UUID userId;

    private final UUID orderId;

    private Boolean valid;

    public OrderValidateUserEvent(UUID orderID, UUID userId, Boolean valid) {
        super();
        this.userId = userId;
        this.orderId = orderID;
       this.valid = valid;
    }
}
