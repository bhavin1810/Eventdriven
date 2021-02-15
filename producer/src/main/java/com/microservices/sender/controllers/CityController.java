package com.microservices.sender.controllers;


import com.microservices.sender.models.CityPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    CityPrices cityPrices;

    @GetMapping(value = "/{cityName}")
    public double getByCityPrice(@PathVariable String cityName)
    {
        return cityPrices.getCityFuelPrice(cityName);
    }
}
