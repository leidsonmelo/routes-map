package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;

public class Stop {

    @Id
    private String id;

    private Position position;

    private Integer vehicleId;

    public Stop() {}

    public Stop(Position position) {
    	this.position = position;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

}