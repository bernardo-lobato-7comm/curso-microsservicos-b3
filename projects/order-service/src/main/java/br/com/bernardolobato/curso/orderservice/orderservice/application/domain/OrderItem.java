package br.com.bernardolobato.curso.orderservice.orderservice.application.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class OrderItem {
    private String name;
    private UUID productId;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem(final Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public UUID getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private OrderItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(productId, orderItem.productId) && Objects.equals(price, orderItem.price) && Objects.equals(name, orderItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, price);
    }
}
