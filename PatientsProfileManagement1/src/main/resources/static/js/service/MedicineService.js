var app = angular.module('myApp');

app.factory('medicineService', function($http){
  var getMedicine = function() {
	    return $http.get("http://localhost:8080/medicine")
	      .then(function(response) {
	        return response.data;
	      });
  };
  var getOneMedicine = function(id){
	  return $http.get("http://localhost:8080/medicine/" + id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var createMedicine = function(medicine){
	  return $http.post("http://localhost:8080/medicine",medicine)
	  .then(function(response){
			return response.data;
		});
  }
  var updateMedicine = function(id,medicine){
	  return $http.put("http://localhost:8080/medicine/"+id,medicine)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteMedicine = function(id){
	  return $http.delete("http://localhost:8080/medicine/"+id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteAllMedicine = function(){
	  return $http.delete("http://localhost:8080/medicine")
	  .then(function(response){
			return response.data;
		});
  }
  
  var getTypeMedicine = function() {
	    return $http.get("http://localhost:8080/typemedicine")
	      .then(function(response) {
	        return response.data;
	      });
};
  return {
	  getMedicine:getMedicine,
	  getOneMedicine:getOneMedicine,
	  createMedicine:createMedicine,
	  updateMedicine:updateMedicine,
	  deleteMedicine:deleteMedicine,
	  deleteAllMedicine:deleteAllMedicine,
	  getTypeMedicine:getTypeMedicine,
  }
});