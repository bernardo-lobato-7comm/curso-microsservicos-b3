package br.com.bernardolobato.curso.productservice.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    UUID id;
    @ManyToOne
    Product product;
    UUID orderId;

    Integer quantity;
}
