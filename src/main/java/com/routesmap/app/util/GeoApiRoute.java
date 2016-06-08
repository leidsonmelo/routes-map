package com.routesmap.app.util;

import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;

public class GeoApiRoute {

	private static final String API_KEY = "AIzaSyCARU2fDc5hpEuAhFXbINkNXDdP4PEcNwI";
	
	private static GeoApiContext context;
	
	public static GeoApiContext getContext(){
		if(context == null)
			new GeoApiContext(new GaeRequestHandler()).setApiKey(API_KEY);
		return context;
	}
	
	
}
