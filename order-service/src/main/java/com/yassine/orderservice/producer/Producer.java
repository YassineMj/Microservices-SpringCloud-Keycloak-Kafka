package com.yassine.orderservice.producer;

import com.yassine.orderservice.dto.DtoOrder;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class Producer {
    private final KafkaTemplate<String, DtoOrder> template;

    public Producer(KafkaTemplate<String, DtoOrder> template) {
        this.template = template;
    }

    public void send(DtoOrder order){
        template.send("Topic-Orders",order.getId(),order);
        System.out.println("📩 OrderService - envoyé order: "+order);
    }
}
