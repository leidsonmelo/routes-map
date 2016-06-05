package com.routesmap.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routesmap.app.model.entity.Vehicle;
import com.routesmap.app.model.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired private VehicleRepository repository; 
	
	public void save(Vehicle vehicle){
		repository.save(vehicle);
	}

	public List<Vehicle> findAll(){
		return repository.findAll();
	}
	
	public void delete(String id){
		repository.delete(id);
	}
	
}
