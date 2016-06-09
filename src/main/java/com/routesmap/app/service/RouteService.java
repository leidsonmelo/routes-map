package com.routesmap.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.model.repository.RouteRepository;
import com.routesmap.app.proxy.GoogleMapsProxy;

@Service
public class RouteService {

	@Autowired private RouteRepository repository;
	
	public Route generateRoute(List<Stop> stops){
		Route route = GoogleMapsProxy.createRoute(stops);
		return route;
	}
	
	public void save(Route route){
		repository.save(route);
	}
	
	public Route findByVehicle(String vehicleId){
		return repository.findByVehicleId(vehicleId);
	}
	
}
