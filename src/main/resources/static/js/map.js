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

routemapApp.controller('RoutemapController', ['$scope', function($scope) {

	angular.extend($scope, {
        london: {
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

    $scope.$on('leafletDirectiveMap.map.click', function(event, teste){
        $scope.markers.push({
        	lat: teste.leafletEvent.latlng.lat,
            lng: teste.leafletEvent.latlng.lng,
            click: function(){
            	alert('teste');
            }
        });
    });

    $scope.$on('leafletDirectiveMarker.map.click', function(event, teste){
        $scope.markers.splice(teste.leafletObject);
    });

}]);