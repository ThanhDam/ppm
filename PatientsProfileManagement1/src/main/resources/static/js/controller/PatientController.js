var app = angular.module('myApp');

app.controller('patientController', function(
        $scope, $interval, $location,$http,patientService,$filter) {
	$scope.patient = {
			name : "",
			address : "",
			sex : "male",
			dob : "",
		};
	$http.get("http://localhost:8080/patient").then(function(response) {
		$scope.patients = response.data;
    });
	
	/////------------get All Patient
	$scope.getPatient = function(data){
		patientService.getPatient().then(getSuccess,getError);
	}
	var getSuccess = function(data) {
		$scope.patients = data;
	};
	var getError = function(error) {
		$scope.error = "Could not find any data"
	};
	
	/////-----------create Patient ------------
	$scope.createPatient = function(){
		patientService.createPatient($scope.patient).then(createSuccess,createError);
	}
	
	var createSuccess = function(data) {
		alert('add new patient Success:' + data.name);
		$scope.getPatient();
	};
	var createError = function(error) {
	};
	
	/////-----------update patient-------------
	$scope.updatePatient = function(id,patient){
		patientService.updatePatient(id,patient).then(updateSuccess,updateError);
	};
	var updateSuccess = function(data) {
		alert('update patient Success:' + data.name);
		$scope.getPatient();
	};
	var updateError = function(error) {
	};
	
	/////-----------delete patient-------------
	$scope.deletePatient= function(id){
		patientService.deletePatient(id).then(deleteSuccess,deleteError);
		
	};
	var deleteSuccess = function(data) {
		alert('delete patient Success:' + data.name);
		$scope.getPatient();
	};
	var deleteError = function(error) {
	};
	
	/////----------get one patient-------------
	$scope.getOne = function(id){
		patientService.getOnePatient(id).then(getOneSuccess,getOneError)
	};
	var getOneSuccess = function(data) {
		$scope.curentPatient = data;
		$scope.date = $filter('date')(new Date($scope.curentPatient.dob), 'yyyy-MM-dd')
	};
	var getOneError = function(error) {
	};
	
	////----------
});