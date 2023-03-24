package br.com.bernardolobato.curso.orderservice.orderservice.infrastructure.adapters.producer;

import br.com.bernardolobato.curso.orderservice.orderservice.application.events.DomainEvent;
import br.com.bernardolobato.curso.orderservice.orderservice.application.port.outbound.OrderEventPublisher;
import io.confluent.shaded.com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SagaCoordinatorMessageProducer implements OrderEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    public SagaCoordinatorMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach((event)-> {
            String eventJson = new Gson().toJson(event);
            this.kafkaTemplate.send(event.getClass().getSimpleName(), event.getId().toString(), eventJson);
            log.info(String.format(event.getClass().getSimpleName(), eventJson));
            System.out.println(eventJson);
        });
    }
}