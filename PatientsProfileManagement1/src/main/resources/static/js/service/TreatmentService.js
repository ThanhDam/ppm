var app = angular.module('myApp');

app.factory('treatmentService', function($http){
  var getTreatment = function() {
	    return $http.get("http://localhost:8080/treatment")
	      .then(function(response) {
	        return response.data;
	      });
  };
  
  var getOneTreatment = function(id){
	  return $http.get("http://localhost:8080/treatment/" + id)
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
  

 
  var createTreatment = function(treatment){
	  return $http.post("http://localhost:8080/treatment",treatment)
	  .then(function(response){
			return response.data;
		});
  }
  var updateTreatment = function(id,treatment){
	  return $http.put("http://localhost:8080/treament/"+id,treatment)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteTreatment = function(id){
	  return $http.delete("http://localhost:8080/treatment/"+id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteAllTreatment = function(){
	  return $http.delete("http://localhost:8080/treatment")
	  .then(function(response){
			return response.data;
		});
  }
  
  return {
	  deleteTreatment: deleteTreatment,
	  getTreatment: getTreatment,
  	  getDetail: getDetail,
  	  createTreatment: createTreatment,
  	  updateTreatment: updateTreatment,
  	  deleteAllTreatment: deleteAllTreatment,
  	  getOneTreatment:getOneTreatment,
  }
});


