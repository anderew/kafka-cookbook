package org.rendell.cookbooks.kafka.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<Integer, TrivalMessage> template;
    private final String topic;


    public KafkaProducer(@Autowired KafkaTemplate<Integer, TrivalMessage> template,
                         @Value("${kafka.simples-messages.topic}") String topic) {
        this.template = template;
        this.topic = topic;
    }

    public void send(TrivalMessage trivalMessage) {

        template.send(topic, trivalMessage.getKey(), trivalMessage);

    }
}
