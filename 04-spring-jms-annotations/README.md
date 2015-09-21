# Exercise 04 - Transacted messages

Enough of JMS boilerplate coding. In this exercise you will be creating a simple JMS application using Spring JMS.

## TODO

* Implement the `Sender` so it sends a message using a `JmsTemplate` to the `testQueue` queue
* Implement the `Receiver` so it listens for messages on the `testQueue` queue using a `@JmsListener` annotation. Add received messages to the `messages` list.

## Testing

There is a `SpringJmsTest` that you can run to test your application.