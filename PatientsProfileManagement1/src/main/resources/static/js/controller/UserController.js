var app = angular.module('myApp');

app.controller('userController', function(
        $scope, $interval, $location,$http,$filter) {
	$scope.user = {
			id : "",
			active: "",
			name : "",
			username : "",
		};
	$http.get("http://localhost:8080/user").then(function(response) {
		$scope.user = response.data;
    });
	
	/////------------get All User
	$scope.getUser = function(data){
		userService.getUser().then(getSuccess,getSuccess);
	}
	var getSuccess = function(data) {
		$scope.user = data;
	};
	var getError = function(error) {
		$scope.error = "Could not find any data"
	};
	
});	
	