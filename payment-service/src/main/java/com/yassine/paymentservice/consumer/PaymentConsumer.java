package com.yassine.paymentservice.consumer;

import com.yassine.paymentservice.dto.DtoOrder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(topics = "Topic-Orders", groupId = "payment-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(DtoOrder dtoOrder) {
        System.out.println("📩 Order reçu: " + dtoOrder);
    }
}
