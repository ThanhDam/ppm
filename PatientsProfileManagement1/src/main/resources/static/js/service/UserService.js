var app = angular.module('myApp');

app.factory('userService', function($http){
	  var getUser = function() {
		    return $http.get("http://localhost:8080/user")
		      .then(function(response) {
		        return response.data;
		      });
	  };
});