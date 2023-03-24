package br.com.bernardolobato.curso.productservice.consumer;

import br.com.bernardolobato.curso.productservice.dto.BookingDTO;
import br.com.bernardolobato.curso.productservice.events.OrderCreatedEvent;
import br.com.bernardolobato.curso.productservice.events.ProductsLockedEvent;
import br.com.bernardolobato.curso.productservice.transactionscript.BookingTransactionScript;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductConsumer {
    @Autowired
    BookingTransactionScript bookingTransactionScript;

    @KafkaListener(topics = "OrderCreatedEvent", groupId = "ProductService")
    public void listen(String in) {
        System.out.println(in);
        OrderCreatedEvent e = new Gson().fromJson(in, OrderCreatedEvent.class);

        e.getItems().forEach((el)-> {
                BookingDTO dto = new BookingDTO(e.getOrderID(), e.getUserID(),  el.getProductId(), el.getQuantity());
                this.bookingTransactionScript.execute(dto);
        });
    }
}
