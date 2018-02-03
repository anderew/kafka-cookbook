package org.rendell.cookbooks.kafka.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class KafkaConsumer {

    @Getter
    List<String> payloadsRecv = new ArrayList<>();

    CountDownLatch latch;
    private int expectingKey;


    /**
     * @param trivalMessage
     * @param key
     * @param offset
     * @param partition     - on previous versions PARTITION_ID worked but on this version it wasnt present in headers
     * @param timestamp     - same as above
     */
    @KafkaListener(topics = "${kafka.simples-messages.topic}")
    public void receive(@Payload TrivalMessage trivalMessage,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                        @Header(KafkaHeaders.OFFSET) Integer offset,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("Received a message with key {} from partition at offset {} with timestamp {}",
                key,
                partition,
                offset,
                timestamp);

        payloadsRecv.add(trivalMessage.getName());

        if (trivalMessage.getKey() == expectingKey) {
            latch.countDown();
        }
    }

    public void clearReceivedMessageStore() {
        payloadsRecv.clear();
    }

    public void prepareToRecv(int key) {
        expectingKey = key;
        latch = new CountDownLatch(1);
    }

    public void blockUntilRecvExpectedOrTimeout() {
        try {
            latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
