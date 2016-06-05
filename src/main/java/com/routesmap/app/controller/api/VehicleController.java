package com.routesmap.app.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.routesmap.app.model.entity.Vehicle;
import com.routesmap.app.service.VehicleService;

@RestController(value = "/vehicle")
public class VehicleController {

	@Autowired private VehicleService vehicleService;
	
	@RequestMapping(value = "/listall", method = RequestMethod.GET)
	public List<Vehicle> listAll(){
		return vehicleService.findAll();
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void save(Vehicle vehicle){
		vehicleService.save(vehicle);
	}
	
	@RequestMapping(value = "/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String id){
		vehicleService.delete(id);
	}
	
}
