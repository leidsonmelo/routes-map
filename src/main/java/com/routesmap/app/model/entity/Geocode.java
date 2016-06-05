package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;

public class Geocode {

    @Id
    private String id;

    private Double lat;
    private Integer lng;

    public Geocode() {}

}