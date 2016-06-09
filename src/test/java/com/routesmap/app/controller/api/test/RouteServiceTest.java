package com.routesmap.app.controller.api.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.routesmap.app.model.entity.Route;
import com.routesmap.app.model.entity.Stop;
import com.routesmap.app.model.factory.StopFactory.Endereco;
import com.routesmap.app.service.RouteService;

public class RouteServiceTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void mustCreateRouteUsingTwoPoints(){
		RouteService routeService = new RouteService();
		
		List<Stop> stops = new ArrayList<Stop>();
		
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL.getStop());
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_BARAO_DE_STUDART.getStop());
		
		Route route = routeService.generateRoute(stops);
		
		Assert.assertEquals("The route must contain 21 coordinates", 21, route.getPath().size());
	}
	
	@Test
	public void mustCreateRouteUsingMoreThanTwoPoints(){
		RouteService routeService = new RouteService();
		
		List<Stop> stops = new ArrayList<Stop>();
		
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL.getStop());
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_BARAO_DE_STUDART.getStop());
		stops.add(Endereco.CRUZAMENTO_DESEMBARGADOR_MOREIRA_X_ABOLICAO.getStop());
		
		Route route = routeService.generateRoute(stops);
		
		Assert.assertEquals("The route must contain 37 coordinates", 37, route.getPath().size());
	}
	

	@Test
	public void mustCreateRouteUsingManyPoints(){
		RouteService routeService = new RouteService();
		
		List<Stop> stops = new ArrayList<Stop>();
		
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL.getStop());
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_BARAO_DE_STUDART.getStop());
		stops.add(Endereco.CRUZAMENTO_DESEMBARGADOR_MOREIRA_X_ABOLICAO.getStop());
		stops.add(Endereco.CRUZAMENTO_HERACLITO_GRACA_X_ILDEFONSO_ALBANO.getStop());
		stops.add(Endereco.CRUZAMENTO_BORGES_DE_MELO_X_LUCIANO_CARNEIRO.getStop());
		
		Route route = routeService.generateRoute(stops);
		
		Assert.assertEquals("The route must contain 113 coordinates", 113, route.getPath().size());
	}
	
	@Test
	public void shouldFailWhenHasJustOnePoint(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("É necessário mais de um ponto para criar uma rota.");
		
		RouteService routeService = new RouteService();
		
		List<Stop> stops = new ArrayList<Stop>();
		
		stops.add(Endereco.CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL.getStop());
		
		routeService.generateRoute(stops);
	}
	
	@Test
	public void shouldFailWhenDoNotHaveAnyPoint(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("É necessário pelo menos dois pontos para a criação de uma rota.");
		
		RouteService routeService = new RouteService();
		
		routeService.generateRoute(new ArrayList<Stop>());
	}
	
}
