package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;

public class Position {

    @Id
    private String id;

    private Double lat;
    private Double lng;

    public Position() {}

}