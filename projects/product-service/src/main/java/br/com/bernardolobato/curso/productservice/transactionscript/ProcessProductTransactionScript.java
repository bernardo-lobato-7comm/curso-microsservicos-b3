package br.com.bernardolobato.curso.productservice.transactionscript;

import br.com.bernardolobato.curso.productservice.dto.BookingDTO;
import br.com.bernardolobato.curso.productservice.entities.Booking;
import br.com.bernardolobato.curso.productservice.entities.Product;
import br.com.bernardolobato.curso.productservice.repository.BookingRepository;
import br.com.bernardolobato.curso.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

public class ProcessProductTransactionScript implements BookingTransactionScript, ConfirmProcessProductTransactionScript {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void execute(BookingDTO dto) {
        Product p = this.productRepository.findById(UUID.fromString(dto.getProductId()))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

        if (p.getQuantity() < dto.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade indisponível");
        }

        List<Booking> bookings = this.bookingRepository.findAllByProductId(dto.getProductId());
        Integer total = bookings.stream().mapToInt((booking)->booking.getQuantity()).sum();

        if (p.getQuantity() < total) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade indisponível");
        }
        Booking b = Booking.builder().product(p).transactionId(dto.getTransactionId()).quantity(dto.getQuantity()).build();
        bookingRepository.save(b);
    }

    @Override
    public void execute(UUID transactionId) {
        Booking booking = this.bookingRepository.findById(transactionId).get();
        Product p = this.productRepository.findById(booking.getProduct().getId()).get();
        p.setQuantity(p.getQuantity() - booking.getQuantity());
        productRepository.save(p);
        bookingRepository.delete(booking);
        //TODO: Add soft delete by status
        //TODO add Domain Events
        //TODO update versioning
    }
}
