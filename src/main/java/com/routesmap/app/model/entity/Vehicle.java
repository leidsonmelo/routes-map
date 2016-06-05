package com.routesmap.app.model.entity;

import org.springframework.data.annotation.Id;

public class Vehicle {

    @Id
    private String id;
    
    private String name;

	public Vehicle() {
		super();
	}
    
    public Vehicle(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    
}
