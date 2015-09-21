# Exercise 05 - Topics and Selectors

In this exercise you will be creating separate applications that communicate through a topic

## Part 1 - Creating the sender and receiver

**TODO**

* The `topic-sender` application must send text messages to the topic `testTopic`. Send `TextMessage`s using a `JmsTemplate`
* The `topic-receiver` application must subscribe to the `testTopic` and log the text messages received

**Testing**

* Start up the `TopicServerApplication` to start a HornetQ server on `localhost:5445`
* Start up the `TopicReceiverApplication` to start the subscriber. Try running multiple instances.
* Start up the `TopicSenderApplication` to send messages to the topic.

## Part 2 - Filtering messages with selectors

**TODO**

* Extend the `topic-sender` so it sets a `number` property on each message that is incremented for each message.
* Extend the `topic-receiver` application so it filters messages based on the `number` property (e.g. number below 20)