package br.com.bernardolobato.curso.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingDTO {
    String userId;
    String productId;
    Integer quantity;
}
