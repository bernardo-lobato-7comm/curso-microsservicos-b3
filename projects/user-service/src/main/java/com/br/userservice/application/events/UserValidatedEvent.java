package com.br.userservice.application.events;


import java.util.UUID;

public class UserValidatedEvent extends DomainEvent {
    private UUID userId;
    private UUID orderId;

    private Boolean valid;


    public UserValidatedEvent(UUID orderId, UUID userId, Boolean valid) {
        super();
        this.userId = userId;
        this.orderId = orderId;
        this.valid = valid;
    }
}
