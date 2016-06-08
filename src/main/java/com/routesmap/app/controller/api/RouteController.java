package com.routesmap.app.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.GeocodingApi;
import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.util.GeoApiRoute;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

	public Route generateRoute(List<Stop> stops){
//		GeocodingApi.newRequest(GeoApiRoute.getContext()).
//		GeoApiRoute.getContext().new
		return null;
	}
	
}
