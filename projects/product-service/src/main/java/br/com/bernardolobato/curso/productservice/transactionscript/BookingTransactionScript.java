package br.com.bernardolobato.curso.productservice.transactionscript;

import br.com.bernardolobato.curso.productservice.dto.BookingDTO;

public interface BookingTransactionScript {
    String execute(BookingDTO dto);
}
