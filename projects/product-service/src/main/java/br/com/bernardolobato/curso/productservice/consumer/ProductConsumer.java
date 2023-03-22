package br.com.bernardolobato.curso.productservice.consumer;

import br.com.bernardolobato.curso.productservice.dto.BookingDTO;
import br.com.bernardolobato.curso.productservice.events.LockProductEvent;
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

    @KafkaListener(topics = "LockProductEvent", groupId = "ProductService")
    public void listen(String in) {
        System.out.println(in);
        LockProductEvent e = new Gson().fromJson(in, LockProductEvent.class);

        BookingDTO dto = new BookingDTO(e.getProductId(), e.getUserId(), e.getQuantity());
        this.bookingTransactionScript.execute(dto);
        System.out.println(e);
    }
}
