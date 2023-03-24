package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import lombok.Data;

import java.util.UUID;
@Data
public class ProductsLockedEvent extends DomainEvent{
    private UUID orderID;
    private UUID userID;

    private boolean isLocked = false;
    private Integer productLockedQuantity = 0;
    private UUID productId;

}
