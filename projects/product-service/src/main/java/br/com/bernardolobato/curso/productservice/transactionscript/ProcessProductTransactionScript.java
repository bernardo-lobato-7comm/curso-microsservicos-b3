package br.com.bernardolobato.curso.productservice.transactionscript;

import br.com.bernardolobato.curso.productservice.dto.BookingDTO;
import br.com.bernardolobato.curso.productservice.entities.Booking;
import br.com.bernardolobato.curso.productservice.entities.Product;
import br.com.bernardolobato.curso.productservice.events.ProductsLockedErrorEvent;
import br.com.bernardolobato.curso.productservice.events.ProductsLockedEvent;
import br.com.bernardolobato.curso.productservice.producer.SagaMessageProducer;
import br.com.bernardolobato.curso.productservice.repository.BookingRepository;
import br.com.bernardolobato.curso.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProcessProductTransactionScript implements BookingTransactionScript, ConfirmProcessProductTransactionScript {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SagaMessageProducer producer;

    @Override
    public String execute(BookingDTO dto) {

        try {
            Product p = this.productRepository.findById(dto.getProductId())
                    .orElseThrow(()->new RuntimeException("Produto não encontrado"));

            if (p.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("Quantidade indisponível");
            }

            List<Booking> bookings = this.bookingRepository.findAllByProductId(dto.getProductId());
            Integer total = bookings.stream().mapToInt((booking)->booking.getQuantity()).sum();

            if (p.getQuantity() < total) {
                throw new RuntimeException("Quantidade indisponível");
            }

            //TODO: save Booking
            Product prod = this.productRepository.findById(dto.getProductId()).get();
            this.bookingRepository.save(new Booking(UUID.randomUUID(), prod, dto.getOrderId(), dto.getQuantity()));

            ProductsLockedEvent event = new ProductsLockedEvent();
            event.setProductId(dto.getProductId());
            event.setLocked(true);
            event.setOrderID(dto.getOrderId());
            event.setUserID(dto.getUserId());
            event.setProductLockedQuantity(dto.getQuantity());

            this.producer.publish(event);
        } catch(RuntimeException ex) {
            ProductsLockedErrorEvent event = new ProductsLockedErrorEvent();
            event.setProductId(dto.getProductId());
            event.setLocked(false);
            event.setOrderID(dto.getOrderId());
            event.setUserID(dto.getUserId());
            event.setProductLockedQuantity(dto.getQuantity());
            this.producer.publish(event);
        }
        return null;
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
