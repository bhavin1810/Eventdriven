# Event Driven Microservice :godmode:

An event driven microservice which has a Kafka Producer and Consumer ; the producer
sends city, lid open or close data to the topic which is received by the consumer.
The producer also sends a random city to the topic every 2 minutes in case not the
user doesn't send it. Once the consumer receives the city and lid open/close data,
it fetches the fuel price for that city.
