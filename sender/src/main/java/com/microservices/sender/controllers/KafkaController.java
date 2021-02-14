package com.microservices.sender.controllers;

import com.microservices.sender.engine.Producer;
import com.microservices.sender.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;
    @Autowired
    Car car;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("lid") boolean lid, @RequestParam("city") String city) {
        car.setLid(lid);
        car.setCity(city);
        this.producer.sendMessage(car);
    }
}
