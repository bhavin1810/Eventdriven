package com.microservices.sender.engine;

import com.microservices.sender.models.CityPrices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class Consumer {

    @Autowired
    CityPrices cityPrices;

    @KafkaListener(topics = "benz", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Received message from topic ->"+message);
        String dissectByComma[] = message.split(",");
        String dissectByEquals[] = dissectByComma[0].split("=");
        String lidValue = dissectByEquals[1];
        dissectByEquals = dissectByComma[1].split("=");
        String city = dissectByEquals[1];
        boolean lid = Boolean.parseBoolean(lidValue);
        double price = 0.0;
        log.info("fetching fuel price for city -> "+city);
        if(lid) {
            price = cityPrices.getCityFuelPrice(city);
            log.info("fuel in "+city+" = "+price+"Rs/L");
        }
        else
            log.info("lid closed. Not fetching fuel price");



    }
}
