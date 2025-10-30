package org.financeproject.service.expenseservice.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventConsumer {
    public KafkaEventConsumer() {}

    @KafkaListener(topics = "${spring.kafka.app.topic}",groupId= "${spring.kafka.consumer.group-id}")
    public void consumer(String message) {
        System.out.println(message);
    }
}
