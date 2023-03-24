package br.com.bernardolobato.curso.productservice.producer;

import br.com.bernardolobato.curso.productservice.events.DomainEvent;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SagaMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void publish(DomainEvent event) {
            String eventJson = new Gson().toJson(event);
            this.kafkaTemplate.send(event.getClass().getSimpleName(), event.getId().toString(), eventJson);
            log.info(String.format(event.getClass().getSimpleName(), eventJson));
            System.out.println(eventJson);
    }
}
