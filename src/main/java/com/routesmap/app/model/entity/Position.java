package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;

public class Position {

    @Id
    private String id;

    private Double lat;
    private Double lng;

    public Position() {
    	
    }
    
    public Position(Double lat, Double lng) {
    	this.lat = lat;
    	this.lng = lng;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

}