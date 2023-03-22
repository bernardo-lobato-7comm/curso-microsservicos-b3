package com.br.userservice.infrastructure.adapters.producers;

import com.br.userservice.application.events.DomainEvent;
import com.br.userservice.application.port.outbound.UserEventPublisher;
import io.confluent.shaded.com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaMessageProducer implements UserEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach((event) -> {
            String eventJson = new Gson().toJson(event);
            this.kafkaTemplate.send(event.getClass().getSimpleName(), event.getId().toString(), eventJson);
            log.info(String.format("Publishing ValidatedUserEvent -> %s", eventJson));
        });
    }
}