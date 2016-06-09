package com.routesmap.app.proxy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.LatLng;
import com.routesmap.app.model.entity.Position;
import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.util.GeoApiRoute;

public class GoogleMapsProxy {

	public static Route createRoute(List<Stop> stops){
		try {
			DirectionsApiRequest request = GeoApiRoute.newRequest();
			request.origin(getOrigin(stops));
			request.destination(getDestination(stops));
			request.waypoints(getWaypoints(stops));
		
			DirectionsResult result = request.await();
			DirectionsRoute directionsRoute = result.routes[0];
			
			List<Position> path = fromLatLng(directionsRoute.overviewPolyline.decodePath());
			return new Route(new Date(), stops, path);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static LatLng getOrigin(List<Stop> stops){
		if(stops == null || stops.isEmpty()){
			throw new RuntimeException("É necessário pelo menos dois pontos para a criação de uma rota.");
		}
		return fromPosition(stops.get(0).getPosition());
	}
	
	public static LatLng getDestination(List<Stop> stops){
		if(stops.size() < 2){
			throw new RuntimeException("É necessário mais de um ponto para criar uma rota.");
		}
		return fromPosition(stops.get(stops.size() - 1).getPosition());
	}
	
	public static String[] getWaypoints(List<Stop> stops){
		List<String> way = new ArrayList<String>();
		for(LatLng latLng : getMidPoints(stops)){
			way.add(latLng.toString());
		}
		return way.toArray(new String[way.size()]);
	}
	
	public static List<LatLng> getMidPoints(List<Stop> stops){
		List<LatLng> latLngs = new ArrayList<LatLng>();
		for(Stop stop : stops.subList(1, stops.size() - 1)){
			latLngs.add(fromPosition(stop.getPosition()));
		}
		return latLngs;
	}
	
	public static LatLng fromPosition(Position position){
		return new LatLng(position.getLat(), position.getLng());
	}
	
	public static Position fromLatLng(LatLng latLng){
		return new Position(latLng.lat, latLng.lng);
	}
	
	public static List<Position> fromLatLng(List<LatLng> latLngs){
		List<Position> positions = new ArrayList<Position>();
		for(LatLng latLng : latLngs){
			positions.add(fromLatLng(latLng));
		}
		return positions;
	}
}
