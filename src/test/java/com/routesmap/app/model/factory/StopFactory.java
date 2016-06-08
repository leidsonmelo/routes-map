package com.routesmap.app.model.factory;

import com.routesmap.app.model.entity.Position;
import com.routesmap.app.model.entity.Stop;

public class StopFactory {

	public enum Endereco {
		CRUZAMENTO_SANTOS_DUMONT_X_DOM_MANUEL(-3.7288509, -38.5211341),
		CRUZAMENTO_SANTOS_DUMONT_X_BARAO_DE_STUDART(-3.7336456, -38.5068494);
		
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
