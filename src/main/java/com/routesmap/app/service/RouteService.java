package com.routesmap.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.proxy.GoogleMapsProxy;

@Service
public class RouteService {

	public Route generateRoute(List<Stop> stops){
		Route route = GoogleMapsProxy.createRoute(stops);
		return route;
	}
	
}
