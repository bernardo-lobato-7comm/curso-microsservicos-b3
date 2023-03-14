package br.com.bernardolobato.curso.productservice.transactionscript;

import br.com.bernardolobato.curso.productservice.dto.BookingDTO;

import java.util.UUID;

public interface ConfirmProcessProductTransactionScript {
    void execute(UUID transactionId);
}
