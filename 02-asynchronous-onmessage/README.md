# Exercise 02 - Asynchronous OnMessage

In this exercise you will be creating an asynchronous `MessageConsumer` with a `MessageListener` and a `MessageProducer`.

## TODO

* Create a `MessageProducer` in the `Sender` class and send a `TextMessage`
* Create a `MessageConsumer` in the `Receiver` class. Refactor the `MessageCapturingMessageListener` so it implements `MessageListener` and stores messages in the `messages` list.
* Set the listener on the consumer
* Start the consumer's connection

## Testing

There is an `AsynchronousOnMessageTest` that you can run to test your application.