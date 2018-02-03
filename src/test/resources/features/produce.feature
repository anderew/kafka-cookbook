Feature: Produce messages which are sent to Kafka topic
  What is the significance of text typed here?

  Background:
    Given the kafka broker is running on localhost

  Scenario: Send a message to kafka and then receive it
    Given a kafka consumer with an empty list of received message
    And a message with a unique key and an attribute which is randomly generated string
    When that message is sent via the kafka producer
    And the kafka consumer waits until it has received a message with same key or times out
    Then the kafka consumers list of message will contain one with the randomly generated string