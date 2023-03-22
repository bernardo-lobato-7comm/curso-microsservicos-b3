package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import br.com.bernardolobato.curso.orderservice.orderservice.application.events.DomainEvent;

import java.util.UUID;

public class ProductRemovedEvent extends DomainEvent {
    public ProductRemovedEvent(UUID id, UUID id1) {
    }
}
