package com.routesmap.app.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.service.RouteService;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

	@Autowired private RouteService routeService;
	
	@RequestMapping(value = "/generate-route", method=RequestMethod.POST)
	public Route generateRoute(@RequestBody List<Stop> stops){
		return routeService.generateRoute(stops);
	}
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public void save(@RequestBody Route route){
		routeService.save(route);
	}
	
	@RequestMapping(value = "/load/{vehicleId}", method=RequestMethod.GET)
	public Route load(@PathVariable String vehicleId){
		return routeService.findByVehicle(vehicleId);
	}
	
}
