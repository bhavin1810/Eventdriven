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
    @Getter public final static double capacity = 30.0;
    @Getter @Setter public static double tank = capacity;

    @Override
    public String toString()
    {
        return "lid ="+lid+
                ",city = "+city+
                ",tank = "+tank;
    }
}
