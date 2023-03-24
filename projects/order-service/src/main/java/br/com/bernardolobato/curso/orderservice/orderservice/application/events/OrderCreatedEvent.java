package br.com.bernardolobato.curso.orderservice.orderservice.application.events;

import br.com.bernardolobato.curso.orderservice.orderservice.application.domain.OrderItem;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderCreatedEvent  extends DomainEvent {
    private final UUID orderID;
    private final UUID userID;

    private final List<OrderCreatedEventItems> items;

    public OrderCreatedEvent(UUID orderID, UUID userID, List<OrderItem> items) {
        super();
        this.orderID = orderID;
        this.userID = userID;

        this.items = items.stream().map((el) -> {
            return new OrderCreatedEventItems(el.getName(), el.getProductId(), el.getPrice(), el.getQuantity());
        }).collect(Collectors.toList());
    }

    @AllArgsConstructor
    class OrderCreatedEventItems {
        private String name;
        private UUID productId;
        private BigDecimal price;
        private Integer quantity;
    }
}
