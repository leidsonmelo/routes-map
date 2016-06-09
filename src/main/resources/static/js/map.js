
routemapApp.controller('RoutemapController', ['$scope', '$rootScope', 'RoutemapService', function($scope, $rootScope, RoutemapService) {

	$scope.route = {};

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
    	if(newVehicle && newVehicle.id){
    		RoutemapService.loadRoute(newVehicle).then(function(route){
    			loadRoute(route);
    		});
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
		var stops = createStops($scope.markers);
		RoutemapService.createRoute(stops).then(function(route){
			if($scope.route && $scope.route.id){
				route.id = $scope.route.id;
				route.name = $scope.route.name;
			}
			$scope.route = route;
			createRoutePath(route.path);
		});
	};

	$scope.openModalSaveRoute = function(){
		$('#modalSaveRoute').modal('show');
	};

	$scope.saveRoute = function(route){
		$scope.route.name = route.name;
		$scope.route.vehicleId = $rootScope.selectedVehicle.id;

		RoutemapService.save(route).then(function(){
			$rootScope.selectedVehicle = {};
			$('#modalSaveRoute').modal('hide');
		});
	};
	
	function loadRoute(route){
		$scope.route = route;
		createMakers(route.stops);
		createRoutePath(route.path);
	}

	function createStops(markers){
		var stops = [];
		for(x = 0; x < markers.length; x++){
			stops.push({
				position : markers[x]
			});
		}
		return stops;
	}

	function createMakers(stops){
		if(stops){
			for(x = 0; x < stops.length; x++){
		        $scope.markers.push(stops[x].position);
			}
		}
	}

    function createRoutePath(overview_path){
        $scope.routePath.p1 = {
            color: 'red',
            weight: 3,
            latlngs: overview_path
        };
    }

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

    $scope.resetRoute = function(){
    	$scope.route = {};
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

routemapApp.service('RoutemapService', ['$http', '$q', function($http, $q) {

	this.createRoute = function(points){
  		return $http.post('/route/generate-route', points).then(function(response){
	     	return response.data;
	    }, function(error){
	      	return $q.reject(error);
	    });
	};

	this.save = function(route){
	    return $http.post('/route/save', route).then(function(response){
	     	return response.data;
	    }, function(error){
	      	return $q.reject(error);
	    });
  	};

  	this.loadRoute = function(vehicle){
		return $http.get('/route/load/' + vehicle.id).then(function(response){
			return response.data;
		});
	};

}]);