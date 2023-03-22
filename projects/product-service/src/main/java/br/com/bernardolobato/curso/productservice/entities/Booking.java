package br.com.bernardolobato.curso.productservice.entities;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Getter
@Builder
public class Booking {
    @Id
    UUID id;
    @ManyToOne
    Product product;
    String transactionId;
    Integer quantity;
}
