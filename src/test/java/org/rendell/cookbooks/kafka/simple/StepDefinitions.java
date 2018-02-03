package org.rendell.cookbooks.kafka.simple;

import cucumber.api.java8.En;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class StepDefinitions implements En {

    private String randomAttributeValue;
    private KafkaConsumer kafkaConsumer;
    private KafkaProducer kafkaProducer;

    private TrivalMessage trivalMessage;
    private int unqiueIdentifier;
    private final List<TrivalMessage> trivalMessages = new ArrayList<>();

    private ApplicationContext context;

    public StepDefinitions() {


        Given("^the kafka broker is running on localhost$", () -> {
            //  Spin up embedded Kafka?
            initialiseBeans();
        });

        Given("^a kafka consumer with an empty list of received message$", () -> {
            kafkaConsumer.clearReceivedMessageStore();
        });

        Given("^a message with a unique key and an attribute which is randomly generated string$", () -> {
            unqiueIdentifier = new Random().nextInt();
            randomAttributeValue = Integer.toHexString(unqiueIdentifier);
            trivalMessage = new TrivalMessage(unqiueIdentifier, randomAttributeValue);
        });

        When("^that message is sent via the kafka producer$", () -> {
            kafkaConsumer.prepareToRecv(unqiueIdentifier);

            kafkaProducer.send(trivalMessage);
        });

        When("^the kafka consumer waits until it has received a message with same key or times out$", () -> {
            kafkaConsumer.blockUntilRecvExpectedOrTimeout();
        });

        Then("^the kafka consumers list of message will contain one with the randomly generated string$", () -> {

            // Using hasItem rather than contains because the topic might have some crap in it from other runs

            assertThat(kafkaConsumer.getPayloadsRecv(), hasItem(randomAttributeValue));
        });

    }

    private void initialiseBeans() {
        context = Application.start();

        kafkaConsumer = context.getBean(KafkaConsumer.class);
        kafkaProducer = context.getBean(KafkaProducer.class);
    }


}
