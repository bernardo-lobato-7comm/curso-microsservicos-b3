package br.com.bernardolobato.curso.productservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    UUID id;
    String name;
    String brand;
    String description;
    Integer quantity;
}
