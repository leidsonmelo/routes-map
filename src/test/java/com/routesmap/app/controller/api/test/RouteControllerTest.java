package com.routesmap.app.controller.api.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.routesmap.app.controller.api.RouteController;
import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.model.factory.StopFactory.Endereco;

public class RouteControllerTest {

	@Test
	public void deveCriarRotaEntreDoisPontos(){
		RouteController routeController = new RouteController();
		
		List<Stop> stops = new ArrayList<Stop>();
		
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL.getStop());
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_BARAO_DE_STUDART.getStop());
		
		Route route = routeController.generateRoute(stops);
		
		Assert.assertEquals("The route must contain 6 coordinates", 6, route.getPath().size());
	}
	
}
