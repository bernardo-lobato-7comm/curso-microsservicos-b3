package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.UUID;

@ToString
public abstract class DomainEvent {

    @Getter
    @Setter
    private UUID id;
    public DomainEvent() {
        this.id = UUID.randomUUID();
//        this.createdTime = new DateTime();
    }
}
