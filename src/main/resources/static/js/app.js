routemapApp = angular.module('RoutemapApp',  ['ngResource', 'ngRoute']);

routemapApp.controller('VehicleController', ['$scope', 'VehicleService', function($scope, VehicleService) {

	$scope.vehicle = {};

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

	function loadVehicles(){
		VehicleService.listAll().then(function(vehicles){
			$scope.vehicles = vehicles;
		});
	}

}]);

routemapApp.service('VehicleService', ['$http', function($http) {

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