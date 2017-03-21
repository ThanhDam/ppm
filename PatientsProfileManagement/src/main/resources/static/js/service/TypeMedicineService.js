var app = angular.module('myApp');

app.factory('typeMedicineService', function($http){
  var getTypeMedicine = function() {
	    return $http.get("http://localhost:8080/typemedicine")
	      .then(function(response) {
	        return response.data;
	      });
  };
  var getOneTypeMedicine = function(id){
	  return $http.get("http://localhost:8080/typemedicine/" + id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var createTypeMedicine = function(typeMedicine){
	  return $http.post("http://localhost:8080/typemedicine",typeMedicine)
	  .then(function(response){
			return response.data;
		});
  }
  var updateTypeMedicine = function(id,typeMedicine){
	  return $http.put("http://localhost:8080/typemedicine/"+id,typeMedicine)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteTypeMedicine = function(id){
	  return $http.delete("http://localhost:8080/typemedicine/"+id)
	  .then(function(response){
			return response.data;
		});
  }
  
  var deleteAllTypeMedicine = function(){
	  return $http.delete("http://localhost:8080/typemedicine")
	  .then(function(response){
			return response.data;
		});
  }
  

  return {
	  getTypeMedicine:getTypeMedicine,
	  getOneTypeMedicine:getOneTypeMedicine,
	  createTypeMedicine:createTypeMedicine,
	  updateTypeMedicine:updateTypeMedicine,
	  deleteTypeMedicine:deleteTypeMedicine,
	  deleteAllTypeMedicine:deleteAllTypeMedicine,
	  getTypeMedicine:getTypeMedicine,
  }
});