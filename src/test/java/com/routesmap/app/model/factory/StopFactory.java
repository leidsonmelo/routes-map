package com.routesmap.app.model.factory;

import com.routesmap.app.model.entity.Position;
import com.routesmap.app.model.entity.Stop;

public class StopFactory {

	public enum Endereco {
		CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL(-3.7287952418135935, -38.52122864685953),
		CRUZAMENTO_SANTOS_DUMONT_X_BARAO_DE_STUDART(-3.7336611615901107, -38.506315564736724),
		CRUZAMENTO_DESEMBARGADOR_MOREIRA_X_ABOLICAO(-3.7273820323417386, -38.49548480473459),
		CRUZAMENTO_HERACLITO_GRACA_X_ILDEFONSO_ALBANO(-3.736696325618049, -38.514496302232146),
		CRUZAMENTO_BORGES_DE_MELO_X_LUCIANO_CARNEIRO(-3.756325606937584, -38.53415152989328);

		private Double lat;
		private Double lng;
		
		private Endereco(Double lat, Double lng) {
			this.lat = lat;
			this.lng = lng;
		}

		public Double getLat() {
			return lat;
		}

		public Double getLng() {
			return lng;
		}
		
		public Stop getStop(){
			return createStop(this);
		}
		
	}
	
	public static Stop createStop(Double lat, Double lng){
		Position position = new Position(lat, lng);
		return new Stop(position);
	}
	
	public static Stop createStop(Endereco endereco){
		return createStop(endereco.getLat(), endereco.getLng());
	}
	
}
