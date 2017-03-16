var app = angular.module('myApp');

app.controller('treatmentDetailController', function(
        $scope, $interval, $location,$http, $routeParams) {
	$scope.count=0;
	
	$http.get("http://localhost:8080/treatment/"+ $routeParams.id).then(function(response) {
		$scope.treatment = response.data;
    });
});
