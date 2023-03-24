package br.com.bernardolobato.curso.orderservice.orderservice.application.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final BigDecimal price;
    private final String name;

    private final Integer quantity;

    @JsonCreator
    public Product(@JsonProperty("id") final UUID id, @JsonProperty("price") final BigDecimal price, @JsonProperty("name") final String name, @JsonProperty("quantity") final Integer quantity) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
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

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(price, product.price) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name);
    }
}