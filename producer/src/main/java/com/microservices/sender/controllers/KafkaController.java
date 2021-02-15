package com.microservices.sender.controllers;

import com.microservices.sender.engine.Producer;
import com.microservices.sender.models.Car;
import com.microservices.sender.models.CityPrices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/producer")
@Api(value = "Kafka Controller", description = "Kafka controller pertaining to different methods")
public class KafkaController {

    private final Producer producer;
    @Autowired
    Car car;

    @Autowired
    CityPrices cityPrices;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @ApiOperation(value = "to publish city and lid open/close status to kafka topic")
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("lid") boolean lid, @RequestParam("city") String city) {
        car.setLid(lid);
        car.setCity(city);
        this.producer.sendMessage(car);
    }

    @ApiOperation(value = "to fetch a list of cities with their fuel prices")
    @GetMapping(value = "/cities")
    public String returnCitiesPricesList(){
        return cityPrices.toString();
    }

    @ApiOperation(value = "to fetch fuel price for a particular city")
    @GetMapping(value = "/city/{cityName}")
    public double getByCityPrice(@PathVariable String cityName)
    {
        return cityPrices.getCityFuelPrice(cityName);
    }

}
