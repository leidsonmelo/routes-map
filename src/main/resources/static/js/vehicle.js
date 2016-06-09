routemapApp.controller('VehicleController', ['$scope', '$rootScope', 'VehicleService', function($scope, $rootScope, VehicleService) {

	$scope.vehicle = {};
	$rootScope.selectedVehicle = {};

	loadVehicles();

	$scope.delete = function(vehicle){
		if(confirm("Deseja excluir o veículo")){
			VehicleService.delete(vehicle).then(function(){
				loadVehicles();
			});
		}
	};

	$scope.save = function(vehicle){
		VehicleService.save(vehicle).then(function(){
			$scope.vehicle = {};
			loadVehicles();
			$('#addVehicleModal').modal('hide');
		});
	};

	$scope.selectVehicle = function(vehicle){
		$rootScope.selectedVehicle = vehicle;
	};

	$scope.deselectVehicle = function(){
		$rootScope.selectedVehicle = {};
	};

	function loadVehicles(){
		VehicleService.listAll().then(function(vehicles){
			$scope.vehicles = vehicles;
		});
	}

}]);

routemapApp.service('VehicleService', ['$http', '$q', function($http, $q) {

	this.listAll = function(){
		return $http.get('/vehicle/listall').then(function(response){
			return response.data;
		});
	};

	this.save = function(vehicle){
	    return $http.post('/vehicle/save', vehicle).then(function(response){
	     	return response.data;
	    }, function(error){
	      	return $q.reject(error);
	    });
  	};

  	this.delete = function(vehicle){
    return $http.delete('/vehicle/delete/' + vehicle.id).then(function(response){
	     	return response.data;
	    }, function(error){
	      	return $q.reject(error);
	    });
  	};

}]);