var app = angular.module('myApp');

app.factory('treatmentService', function($http){
	
	 var createAllergics = function(allergics){
		  return $http.post("http://localhost:8080/addAllergicList",allergics)
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
	 
	 var getAllTreatment = function(){
		 return  $http.get("http://localhost:8080/treatment")
		  .then(function(response){
				return response.data;
			});
	 }
	 var getOneTreatment = function(id){
		  return $http.get("http://localhost:8080/treatment/" + id)
		  .then(function(response){
				return response.data;
			});
	  }
	 var updateTreatment = function(id,treatment){
		  return $http.put("http://localhost:8080/treatment/"+id,treatment)
		  .then(function(response){
				return response.data;
			});
	  }
	 
	 return{
		 createAllergics:createAllergics,
		 getAllTreatment:getAllTreatment,
		 createTreatment:createTreatment,
		 getOneTreatment:getOneTreatment,
		 updateTreatment:updateTreatment,
	 }
	
});