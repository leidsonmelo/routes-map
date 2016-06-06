/*var map = L.map('mapid').setView([-3.75, -38.53], 13);

L.tileLayer(
	'https://api.mapbox.com/styles/v1/mapbox/emerald-v8/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoibGVpZHNvbm1lbG8iLCJhIjoiY2lwMnB1cXhwMDBnenRzbTB1ZXlkZ3R3dyJ9.jgUFPdP-vRqRVoe92p1RAQ',
    attribution: '© <a href="https://www.mapbox.com/map-feedback/">Mapbox</a> © <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);

map.on('click', function(e){
	L.marker().setLatLng(e.latlng)
	.on('click', function(){
		map.removeLayer(this);
	})
	.addTo(map);
});*/

routemapApp.controller('RoutemapController', ['$scope', '$rootScope', function($scope, $rootScope) {

	angular.extend($scope, {
        fortaleza: {
            lat: -3.75,
            lng: -38.53,
            zoom: 13
        },
        tiles: {
            url: 'https://api.mapbox.com/styles/v1/mapbox/emerald-v8/tiles/{z}/{x}/{y}?access_token={accessToken}',
            type: 'xyz',
            options: {
                apikey: 'pk.eyJ1IjoibGVpZHNvbm1lbG8iLCJhIjoiY2lwMnB1cXhwMDBnenRzbTB1ZXlkZ3R3dyJ9.jgUFPdP-vRqRVoe92p1RAQ'
            }
        },
        markers: []
    });

    $scope.$on('leafletDirectiveMap.map.click', function(event, args){
    	if($rootScope.selectedVehicle && $rootScope.selectedVehicle.id){
	    	var icon = generateIcon(args.leafletEvent);
	        $scope.markers.push(icon);
    	}else{
    		alert('É necessário selecionar um veículos antes de criar a rota');
    	}
    });

    $scope.$on('leafletDirectiveMarker.map.click', function(event, args){
    	removeIcon(args.leafletEvent.latlng);
    });

    function generateIcon(event){
    	return {
        	lat: event.latlng.lat,
            lng: event.latlng.lng
        };
    }

    function removeIcon(latlng){
    	var position = findPositionIcon(latlng.lat, latlng.lng);
    	if(position >= 0){
    		$scope.markers.splice(position, 1);
    	}
    }

    function findPositionIcon(lat, lng){
    	var position;
    	for(x = 0; x < $scope.markers.length; x ++){
    		if($scope.markers[x].lat === lat && $scope.markers[x].lng === lng){
    			position = x;
    		}
    	}
    	return position;
    }

}]);