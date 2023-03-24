package br.com.bernardolobato.curso.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class BookingDTO {
    UUID orderId;
    UUID userId;
    UUID productId;
    Integer quantity;
}
