package com.br.userservice.application.events;

import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.UUID;

public abstract class DomainEvent {

    @Getter
    private String id;
    public DomainEvent() {
        this.id = UUID.randomUUID().toString();
    }
}
