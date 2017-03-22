var app = angular.module('myApp');

app.factory('userService', function($http){

///----------------get Admin ------------------------
  var getAdmin = function() {
	    return $http.get("http://localhost:8080/userbyrole/1")
	      .then(function(response) {
	        return response.data;
	      });
  };
 ///----------------get Doctor ------------------------
  var getDoctor = function(){
	return $http.get("http://localhost:8080/userbyrole/2")  
		.then(function(response){
			return response.data;
		});
			
  }
///----------------get Nurse ------------------------
  var getNurse = function(){
		return $http.get("http://localhost:8080/userbyrole/3")  
			.then(function(response){
				return response.data;
			});
				
	  }

  ///----------------get one user by id ------------------------
  var getOneUser = function(id){
	  return $http.get("http://localhost:8080/user/" + id)
	  .then(function(response){
			return response.data;
		});
  }
  

///----------------Delete user by Id ------------------------
  var deleteUser = function(id){
	  return $http.delete("http://localhost:8080/user/"+id)
	  .then(function(response){
			return response.data;
		});
  }
///----------------Update user by Id ------------------------
  var updateUser = function(id,user){
	  return $http.put("http://localhost:8080/user/"+id,user)
	  .then(function(response){
			return response.data;
		});
  }
///---------------Get login user-----------------------------
  var getLoginUser = function(){
	  return $http.put("http://localhost:8080/userlogged")
	  .then(function(response){
			return response.data;
		});
  }
  ///-----------------get current user----------------
  var currentUser = function(){
	  return $http.get("http://localhost:8080/userProfile")
	  .then(function(response){
		  return response.data;
	  });
  }
///-----------------Change password----------------
	  var changePass = function(user){
		  return $http.put("http://localhost:8080/changePass",user)
		  .then(function(response){
			  return response.data;
		  });
  }
  return{
	  getAdmin:getAdmin,
	  getDoctor:getDoctor,
	  getNurse:getNurse,
	  getOneUser:getOneUser,
	  deleteUser:deleteUser,
	  updateUser:updateUser,
	  getLoginUser:getLoginUser,
	  currentUser:currentUser,
	  changePass:changePass,
  }
  	
});