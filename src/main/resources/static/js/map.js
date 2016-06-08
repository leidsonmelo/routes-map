
routemapApp.controller('RoutemapController', ['$scope', '$rootScope', 'RoutemapService', function($scope, $rootScope, RoutemapService) {

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
        routePath: {},
        markers: []
    });

    $rootScope.$watch('selectedVehicle', function(newVehicle, oldVehicle){
    	if(!newVehicle || newVehicle.id !== oldVehicle.id){
			$scope.resetRoute();
    	}
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

    $scope.createRoute = function(){
		var directionsService = new google.maps.DirectionsService;
		directionsService.route({
		    origin: getOriginRoute($scope.markers),
		    destination: getDestinationRoute($scope.markers),
		    waypoints: getWaypointsRoute($scope.markers),
		    travelMode: google.maps.TravelMode['DRIVING']
		}, function(response, status) {
		    if (status == google.maps.DirectionsStatus.OK) {
		        $scope.routePath.p1 = {
                    color: 'red',
                    weight: 3,
                    latlngs: getArrayPoints(response.routes[0].overview_path)
                };
		    } else {
		      window.alert('Directions request failed due to ' + status);
		    }
		});
	};

	function getOriginRoute(markers){
		return {lat: markers[0].lat, lng: markers[0].lng};
	}

	function getDestinationRoute(markers){
		return {lat: markers[markers.length - 1].lat, lng: markers[markers.length - 1].lng};
	}

	function getWaypointsRoute(markers){
		var waypoints = [];
		if(markers && markers.length > 2){
			for(x = 1; x < markers.length - 1; x ++){
				waypoints.push({
					location : {
						lat: markers[x].lat,
						lng: markers[x].lng,
					}
				});
			}
		}
		return waypoints;
	}

    function getArrayPoints(overview_path){
    	var arrayPoints = [];
    	for(x = 0; x < overview_path.length; x++){
	    	var icon = {
	        	lat: overview_path[x].lat(),
	            lng: overview_path[x].lng()
	        };
        	arrayPoints.push(icon);
        }
        return arrayPoints;
    }

    $scope.resetRoute = function(){
		$scope.markers = [];
		$scope.routePath= {};
    };

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

routemapApp.service('RoutemapService', ['$http', function($http) {

	this.createRoute = function(points){
		var responseRequest;
  		var directionsService = new google.maps.DirectionsService;
  		directionsService.route({
			origin: {lat: 37.77, lng: -122.447},
		    destination: {lat: 37.768, lng: -122.511}, 
		    travelMode: google.maps.TravelMode['DRIVING']
		}, function(response, status) {
		    if (status == google.maps.DirectionsStatus.OK) {
		      responseRequest = response;
		    } else {
		      window.alert('Directions request failed due to ' + status);
		    }
		});
  		return responseRequest;
	};

}]);