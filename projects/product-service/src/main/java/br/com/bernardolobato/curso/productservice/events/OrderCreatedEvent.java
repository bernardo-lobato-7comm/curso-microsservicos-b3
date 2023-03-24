package br.com.bernardolobato.curso.productservice.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class OrderCreatedEvent extends DomainEvent {

    private List<OrderCreatedEventItems> items;
    private UUID orderID;
    private UUID userID;


}
