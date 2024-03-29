package br.com.bernardolobato.curso.orderservice.orderservice.application.domain;

import br.com.bernardolobato.curso.orderservice.orderservice.application.events.*;
import br.com.bernardolobato.curso.orderservice.orderservice.application.exceptions.DomainException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Order {
    private UUID id;
    private UUID userId;
    private OrderStatus status;
    private List<OrderItem> orderItems;
    private BigDecimal price;
    private List<DomainEvent> events = new ArrayList<>();;

    public Order(final UUID id, final UUID userId, final Product product) {
        this.id = id;
        this.userId = userId;
//        this.orderItems = Arrays.asList(new OrderItem(product));
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(product));
        this.orderItems = items;
        this.status = OrderStatus.CREATED;
        this.price = product.getPrice();
        this.events.add(new OrderCreatedEvent(this.id, this.userId, items));
    }

    public Order(final UUID id, final UUID userId, final List<Product> products) {
        this.id = id;
        this.orderItems = products.stream().map(OrderItem::new).collect(Collectors.toList());
        this.status = OrderStatus.CREATED;
        this.price = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.events.add(new OrderCreatedEvent(this.id, userId, orderItems));
    }

    public void complete() {
        validateState();
        this.status = OrderStatus.COMPLETED;
        this.events.add(new OrderCompletedEvent(this.id));
    }

    public void addProduct(final Product product) {
        validateState();
        validateProduct(product);
        orderItems.add(new OrderItem(product));
        price = price.add(product.getPrice());
        this.events.add(new OrderCreatedEvent(this.id, this.userId, orderItems));
    }

    public void removeProduct(final UUID id) {
        validateState();
        final OrderItem orderItem = getOrderItem(id);
        orderItems.remove(orderItem);
        price = price.subtract(orderItem.getPrice());
        this.events.add(new ProductRemovedEvent(this.id, id));
    }

    private OrderItem getOrderItem(final UUID id) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.getProductId()
                        .equals(id))
                .findFirst()
                .orElseThrow(() -> new DomainException("Product with " + id + " doesn't exist."));
    }

    private void validateState() {
        if (OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state.");
        }
    }

    private void validateProduct(final Product product) {
        if (product == null) {
            throw new DomainException("The product cannot be null.");
        }
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItems, price, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Order))
            return false;
        Order other = (Order) obj;
        return Objects.equals(id, other.id) && Objects.equals(orderItems, other.orderItems) && Objects.equals(price, other.price) && status == other.status;
    }

    private Order(){}

    public List<DomainEvent> getEvents() {
        return this.events;
    }
    public void clearEvents() {
        this.events.clear();
    }

    public void setUserValidated() {
        this.status = OrderStatus.USER_VALIDATED;
        this.events.add(new OrderValidateUserEvent(this.id, this.userId, true));
    }

    public void lockProducts() {
        this.status = OrderStatus.PRODUCTS_LOCKED;

        this.getOrderItems().forEach((item)-> {
            this.events.add(new LockProductEvent(this.id, this.userId, 1));
        });
    }
}
