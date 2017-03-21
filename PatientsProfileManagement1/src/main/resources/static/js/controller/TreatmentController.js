var app = angular.module('myApp');

app.controller('treatmentController', function(
        $scope, $interval, $location,$http, $routeParams, patientService, $log, $timeout) {
	
	$scope.treatment = {
			patientId :"" ,
			doctorId :"",
			prescription : "",
			file:"",
		};
	
	$http.get("http://localhost:8080/treatment").then(function(response) {
		$scope.treatments = response.data;
    });
	
	// ///------------get All Treatment
// $scope.getTreatment = function(data){
// treatmentService.getTreatment().then(getSuccess,getError);
// }
// var getSuccess = function(data) {
// $scope.treatments = data;
// };
// var getError = function(error) {
// $scope.error = "Could not find any data"
// };
	
 ///------------get Id Patient
	
	$scope.getOnePatient = function(id){
		patientService.getOnePatient(id).then(getOneSuccess,getOneError)
	};
	var getOneSuccess = function(data) {
		$scope.patient = data;

	};
	var getOneError = function(error) {
	};
	
///------------get name doctor
	$http.get("http://localhost:8080/treatment/name").then(function(response) {
		$scope.doctor = response.data;
    });
	
	 ///-----------create treatment ------------
//	$scope.treat.patientId=patient.id;
//	$scpoe.treat.doctorId=user.id;
//	$scope.treat.date= new Date();
//	$scope.treat.file="";
	
	$http.get("http://localhost:8080/treatment", $scope.treatment).then(function(response) {
		$scope.treatment= response.data;
    });
	
	///--------------get all medicine-----------
	$http.get("http://localhost:8080/medicine").then(function(response) {
		$scope.medicine= response.data;
    });

	var self = this;

    self.simulateQuery = false;
    self.isDisabled    = false;

    self.repos         = loadAll();
    self.querySearch   = querySearch;
    self.selectedItemChange = selectedItemChange;
    self.searchTextChange   = searchTextChange;

    // ******************************
    // Internal methods
    // ******************************

    /**
     * Search for repos... use $timeout to simulate
     * remote dataservice call.
     */
    function querySearch (query) {
      var results = query ? self.repos.filter( createFilterFor(query) ) : self.repos,
          deferred;
      if (self.simulateQuery) {
        deferred = $q.defer();
        $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
        return deferred.promise;
      } else {
        return results;
      }
    }

    function searchTextChange(text) {
      $log.info('Text changed to ' + text);
    }

    function selectedItemChange(item) {
      $log.info('Item changed to ' + JSON.stringify(item));
    }

    /**
     * Build `components` list of key/value pairs
     */
    function loadAll() {
     
     return $http.get("http://localhost:8080/medicine")
      .then(function(response) {
	        return response.data;
	      });
    }

    /**
     * Create filter function for a query string
     */
    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);

      return function filterFn(item) {
        return (item.value.indexOf(lowercaseQuery) === 0);
      };

    }
});