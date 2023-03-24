package br.com.bernardolobato.curso.productservice.events;

import lombok.Getter;

import java.util.UUID;

public abstract class DomainEvent {

    @Getter
    private String id =UUID.randomUUID().toString();
}
