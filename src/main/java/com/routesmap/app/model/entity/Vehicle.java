package com.routesmap.app.model.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Vehicle implements Serializable {

	private static final long serialVersionUID = 7112075050958964737L;

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
 
}
