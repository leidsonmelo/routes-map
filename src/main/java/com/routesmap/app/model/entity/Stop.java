package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;

public class Stop {

    @Id
    private String id;

    private Position position;

    private Integer vehicleId;

    public Stop() {}

}