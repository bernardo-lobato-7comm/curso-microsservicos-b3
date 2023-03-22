package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import java.util.UUID;

public class LockProductEvent extends DomainEvent {
    UUID userId;
    UUID productId;
    Integer quantity;

    public LockProductEvent(UUID userId, UUID productId, int quantity) {
        super();
        this.userId = userId;
        this.productId = productId;
        this.quantity = 1;
    }
}
