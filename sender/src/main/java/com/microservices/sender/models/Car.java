package com.microservices.sender.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Car {
    @Getter @Setter private boolean lid = false;
    @Getter @Setter private String city;
    @Getter @Setter private static int capacity = 30;

    @Override
    public String toString()
    {
        return "lid="+lid+
                ",city="+city+
                ",capacity="+capacity;
    }
}
