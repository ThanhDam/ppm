var app = angular.module('myApp');

app.factory('patientService', function($http){
  var getPatient = function() {
	    return $http.get("http://localhost:8080/patient")
	      .then(function(response) {
	        return response.data;
	      });
  };
  
  var getOnePatient = function(id){
	  return $http.get("http://localhost:8080/patient/" + id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var getNameDoctor = function(){
	  return $http.get("http://localhost:8080/treatment/name)
	  .then(function(response){
			return response.data;
		});
  }
  
  var getDetail = function(id) {
	  return $http.get("http://localhost:8080/patient/" + id)
	  			.then(function(response){
					return response.data;
				});
  }
  var getPatientTrament = function(id) {
	  return $http.get("http://localhost:8080/patient/" + id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var createPatient = function(patient){
	  return $http.post("http://localhost:8080/patient",patient)
	  .then(function(response){
			return response.data;
		});
  }
  var updatePatient = function(id,patient){
	  return $http.put("http://localhost:8080/patient/"+id,patient)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deletePatient = function(id){
	  return $http.delete("http://localhost:8080/patient/"+id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteAllpatient = function(){
	  return $http.delete("http://localhost:8080/patient")
	  .then(function(response){
			return response.data;
		});
  }
  
  return {
	  deletePatient: deletePatient,
	  getPatient: getPatient,
  	  getDetail: getDetail,
  	  createPatient: createPatient,
  	  updatePatient: updatePatient,
  	  deleteAllpatient: deleteAllpatient,
  	  getOnePatient:getOnePatient,
  }
});


