var app = angular.module('myApp');

app.controller('userController', function(
        $scope, $interval, $location,$http) {
	
	$http.get("http://localhost:8080/user").then(function(response) {
		$scope.users = response.data;
    });
});