package com.microservices.producer.engine;

import com.microservices.producer.models.Car;
import com.microservices.producer.models.CityPrices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class Producer {

    private static final String TOPIC = "benz";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    Car car;

    @Autowired
    CityPrices cityPrices;

    @Scheduled(fixedDelay = 120000)
    public void randomCity()
    {
        car.setCity(cityPrices.getRandomCity());
        Random random = new Random();
        car.setLid(random.nextBoolean());
        sendMessage(car);
    }


    public void sendMessage(Car car) {
        log.info("Sending message to topic->"+ car.toString());
        this.kafkaTemplate.send(TOPIC, car.toString());
    }

}
