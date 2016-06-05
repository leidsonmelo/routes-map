package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;
import java.util.List;
import java.util.Date;

public class Route {

    @Id
    private String id;

    private String name;
    private Date routeDate;
    private Integer vehicleId;
    private List<Stop> stops;
    private List<Geocode> path;

    public Route() {}

}