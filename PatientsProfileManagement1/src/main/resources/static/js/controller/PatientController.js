var app = angular.module('myApp');

app.controller('patientController', function(
        $scope, $interval, $location,$http,patientService,$filter) {
	$scope.patient = {
			name : "",
			address : "",
			sex : "male",
			dob : "",
		};
	
	$scope.patients = [];
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;
		$scope.reverse = !$scope.reverse;
	}
	
	$http.get("http://localhost:8080/patient").then(function(response) {
		$scope.patients = response.data;
    });
	
	/////------------get All Patient
	$scope.getPatient = function(data){
		patientService.getPatient().then(getSuccess,getSuccess);
	}
	var getSuccess = function(data) {
		$scope.patients = data;
	};
	
	/*convert = function(dob){
		var date = dob.substring(8,10);
		var month = dob.substring(5,7);
		var year = dob.substring(0,4);
		var day = date + "-" + month + "-" + year;
		return day;
		
	};*/
	
	var getError = function(error) {
		$scope.error = "Could not find any data"
	};
	
	/////-----------create Patient ------------
	$scope.createPatient = function(){
		patientService.createPatient($scope.patient).then(createSuccess,createError);
		
	}
	
	var createSuccess = function(data) {
		bootbox.alert({
			message: "Add Patient Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getPatient();
	};
	var createError = function(error) {
		bootbox.alert({
			message: "Add Patient Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
	/////-----------update patient-------------
	$scope.updatePatient = function(id,patient){
		patientService.updatePatient(id,patient).then(updateSuccess,updateError);
	};
	var updateSuccess = function(data) {
		bootbox.alert({
			message: "Update Patient Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getPatient();
	};
	var updateError = function(error) {
		bootbox.alert({
			message: "Update Patient Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
	/////-----------delete patient-------------
	$scope.deletePatient= function(id){
		patientService.deletePatient(id).then(deleteSuccess,deleteError);
		
	};
	var deleteSuccess = function(data) {
		bootbox.alert({
			message: "Delete Patient Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getPatient();
	};
	var deleteError = function(error) {
		bootbox.alert({
			message: "Delete Patient Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
	/////----------get one patient-------------
	$scope.getOne = function(id){
		patientService.getOnePatient(id).then(getOneSuccess,getOneError)
	};
	var getOneSuccess = function(data) {
		$scope.curentPatient = data;
		$scope.curentPatient.dob = new Date(data.dob);
	};
	var getOneError = function(error) {
	};
	
	
	////----------
});