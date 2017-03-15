var app = angular.module('myApp');

app.controller('treatmentController', function(
        $scope, $interval, $location,$http, $routeParams, patientService) {
	
	
	$scope.treatment = {
			idPatient : "",
			idDoctor : "",
			prescription : "",
			
		};
	$http.get("http://localhost:8080/treatment").then(function(response) {
		$scope.treatments = response.data;
    });
	
	/////------------get All Treatment
//	$scope.getTreatment = function(data){
//		treatmentService.getTreatment().then(getSuccess,getError);
//	}
//	var getSuccess = function(data) {
//		$scope.treatments = data;
//	};
//	var getError = function(error) {
//		$scope.error = "Could not find any data"
//	};
	
/////------------get Id Patient
	$http.get("http://localhost:8080/treatment").then(function(response) {
		$scope.pat = response.data;
    });
	$scope.getOnePatient = function(id){
		patientService.getOnePatient(id).then(getOneSuccess,getOneError)
	};
	var getOneSuccess = function(data) {
		$scope.patient = data;

	};
	var getOneError = function(error) {
	};
	
/////------------get name doctor
	$http.get("http://localhost:8080/treatment/name").then(function(response) {
		$scope.doctor = response.data;
    });
	
	

});