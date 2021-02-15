package com.microservices.sender.models;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class CityPrices {

    @Getter @Setter String city;
    @Getter @Setter private HashMap<String,Double> listOfCities = new HashMap<String, Double>();

    public CityPrices()
    {
        initializeCityPrices();
    }

    @Cacheable("cities")
    public double getCityFuelPrice(String city)
    {
        if(listOfCities.containsKey(city))
            return listOfCities.get(city);
        else
            return 0.0;
    }

    public String getRandomCity()
    {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(listOfCities.keySet());
        return keys.get(random.nextInt(keys.size()));
    }

    private void initializeCityPrices()
    {
        listOfCities.put("bangalore",91.7);
        listOfCities.put("chennai",90.96);
        listOfCities.put("delhi",88.14);
        listOfCities.put("mumbai",94.64);
        listOfCities.put("goa",85.01);
    }
}
