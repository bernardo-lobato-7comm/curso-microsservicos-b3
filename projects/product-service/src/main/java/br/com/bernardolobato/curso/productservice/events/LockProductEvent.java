package br.com.bernardolobato.curso.productservice.events;

import lombok.Data;

@Data
public class LockProductEvent extends DomainEvent {

    String userId;
    String productId;
    Integer quantity;

}
