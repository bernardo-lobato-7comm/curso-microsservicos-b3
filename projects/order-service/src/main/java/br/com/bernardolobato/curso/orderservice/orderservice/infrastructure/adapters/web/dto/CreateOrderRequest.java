package br.com.bernardolobato.curso.orderservice.orderservice.infrastructure.adapters.web.dto;

import br.com.bernardolobato.curso.orderservice.orderservice.application.domain.Product;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class CreateOrderRequest {
    private UUID userId;
    private List<Product> products;

    @JsonCreator
    public CreateOrderRequest(@JsonProperty("userId") final UUID userId, @JsonProperty("products") final List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
    public UUID getUserId() {
        return this.userId;
    }
}