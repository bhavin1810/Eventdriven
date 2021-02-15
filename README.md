# Event Driven Microservice :godmode:

An event driven microservice which has a Kafka Producer and Consumer ; the producer
sends city, lid open or close data to the topic which is received by the consumer.
The producer also sends a random city to the topic every 2 minutes in case not the
user doesn't send it. Once the consumer receives the city and lid open/close data,
it fetches the fuel price for that city and calculates fuel consumed, how much fuel
filled and in how many seconds in the car's tank.

#Setup :trollface:
Producer:
Add the following environment variables for producer:
```
spring.config.location = /path_to_producer/producer/src/main/resources/application.yml
```
Consumer:
Add the following environment variables for consumer:
```
spring.config.location = /path_to_consumer/consumer/src/main/resources/application.yml
```

#Build :goberserk:
For both producer and consumer build the following way:
```
mvn clean install
```

#SWAGGER :link:
http://localhost:9000/swagger-ui.html

