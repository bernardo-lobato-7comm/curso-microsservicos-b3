package br.com.bernardolobato.curso.productservice.events;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderCreatedEventItems {
    private String name;
    private UUID productId;
    private BigDecimal price;

    private Integer quantity;

    public Integer getQuantity() {
        if (this.quantity == null) {
            return 0;
        }
        else return this.quantity;
    }
}
