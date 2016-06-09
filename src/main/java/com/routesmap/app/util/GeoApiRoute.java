package com.routesmap.app.util;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;

public class GeoApiRoute {

	private static final String API_KEY = "AIzaSyCARU2fDc5hpEuAhFXbINkNXDdP4PEcNwI";
	
	private static GeoApiContext context;
	
	public static GeoApiContext getContext(){
		if(context == null)
			context = new GeoApiContext().setApiKey(API_KEY);
		return context;
	}
	
	public static DirectionsApiRequest newRequest(){
		return DirectionsApi.newRequest(getContext());
	}
	
}
