var app = angular.module('myApp');

app.controller('patientController', function(
        $scope, $interval, $location,$http) {
	
	$http.get("http://localhost:8080/patient").then(function(response) {
		$scope.patients = response.data;
    });

});