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
    private List<Position> path;

    public Route() {
    	
    }
    
    public Route(String name, Date routeDate, Integer vehicleId, List<Stop> stops, List<Position> path){
    	this.name = name;
    	this.routeDate = routeDate;
    	this.vehicleId = vehicleId;
    	this.stops = stops;
    	this.path = path;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRouteDate() {
		return routeDate;
	}

	public void setRouteDate(Date routeDate) {
		this.routeDate = routeDate;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public List<Stop> getStops() {
		return stops;
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

	public List<Position> getPath() {
		return path;
	}

	public void setPath(List<Position> path) {
		this.path = path;
	}

}