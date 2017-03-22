var app = angular.module('myApp');

app.factory('allergicService', function($http){
	
	 var createAllergics = function(allergics,patienttId){
		 
		  return $http.post("http://localhost:8080/allergic/List",allergics,treatmentId)
		  .then(function(response){
				return response.data;
			});
	  }
	 
	 var createAllergic = function(allergic){
		 return $http.post("http://localhost:8080/addAllergic",allergic)
		 .then(function(response){
				return response.data;
			});
	 }
	 
	 var dleteAllergic = function(id){
		 return $http.delete("http://localhost:8080/allergic/"+id)
		  .then(function(response){
				return response.data;
			});
	 }
	 
	 return{
		 createAllergic:createAllergic,
		 createAllergics:createAllergics,
		 dleteAllergic:dleteAllergic,
	 }
	
});