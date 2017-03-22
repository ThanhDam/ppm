var app = angular.module('myApp');

app.controller('allergicController', function(
        $scope, $interval, $location,allergicService) {
	
	$scope.allergic={
			medicineId:"",
			patientId:{}
			
	};
	
	$scope.allergiclist = [];
	
	
	
	$scope.saveListAllergic = function(){
		allergicService.createAllergics($scope.allergiclist).then(createAllergicsSuccess,createAllergicsError)
	};
	
	var createAllergicsSuccess = function(data) {
		$scope.medicines = data;   
	};
	var createAllergicsError = function(error) {
		$scope.error = "Could not find any data"
	};

});