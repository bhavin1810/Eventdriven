package com.microservices.consumer.engine;

import com.microservices.consumer.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class Consumer {
    @Autowired
    Car car;

    private final static DecimalFormat df = new DecimalFormat("0.00");

    @KafkaListener(topics = "benz", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Received message from topic ->"+message);
        String dissectByComma[] = message.split(",");
        String dissectByEquals[] = dissectByComma[0].split("=");
        String lidValue = dissectByEquals[1];
        dissectByEquals = dissectByComma[1].split("=");
        String city = dissectByEquals[1];
        city = city.replaceFirst(" ","");
        boolean lid = Boolean.parseBoolean(lidValue);
        double price = 0.0;
        double fuelSpent = getLitresConsumed();
        log.info("fuel consumed in travelling to "+city+" = "+fuelSpent+" litres");
        Car.tank = Double.parseDouble(df.format(Car.tank - fuelSpent));
        log.info("remaining fuel left = "+Car.tank+ " litres");
        if(lid) {
            price = getCityPrice(city);
            log.info("fuel in "+city+" = "+price+"Rs/L");
            double filled = getLitresFilled();
            double timeTaken = Double.parseDouble(df.format(filled * 30));
            log.info("filling "+filled+" litres in "+timeTaken+" seconds");
            log.info("total fuel in tank = "+Double.parseDouble(df.format((Car.tank+filled)))+"litres ; total cost = "+Double.parseDouble(df.format((price * filled)))+" Rs");
        }
        else
            log.info("lid closed. Not fetching fuel price nor filling fuel for "+city);
    }

    private double getLitresConsumed()
    {
        double randomConsumption = ThreadLocalRandom.current().nextDouble(0, Car.tank);
        return Double.parseDouble(df.format(randomConsumption));
    }

    private double getLitresFilled()
    {
        double randomFilling = ThreadLocalRandom.current().nextDouble(1, Car.capacity);
        if((randomFilling+Car.tank) > Car.capacity)
            getLitresFilled();
        return Double.parseDouble(df.format(randomFilling));
    }

    private double getCityPrice(String city)
    {
        String requestUrl = "http://localhost:9000/producer/city/"+city;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl,String.class);
        return Double.parseDouble(response.getBody());
    }
}
