var app = angular.module('myApp',[]);

app.directive('validPasswordC', function() {
	  return {
	    require: 'ngModel',
	    scope: {

	      reference: '=validPasswordC'

	    },
	    link: function(scope, elm, attrs, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue, $scope) {

	        var noMatch = viewValue != scope.reference
	        ctrl.$setValidity('noMatch', !noMatch);
	        return (noMatch)?noMatch:!noMatch;
	      });

	      scope.$watch("reference", function(value) {;
	        ctrl.$setValidity('noMatch', value === ctrl.$viewValue);

	      });
	    }
	  }
	});

app.controller('contactlistController', function($scope) {




});
//app.controller('contactlistController', function(
//        $scope, $interval, $location,medicineService, typeMedicineService,$http,$log) {
//
//
//
////	$scope.repos         = loadAllAC();
//	$scope.searchText = null;
//	$scope.selectedItem = null;
//	$scope.querySearchAC   = querySearchAC;
//
//    // ******************************
//    // Internal methods
//    // ******************************
//
//    /**
//     * Search for repos... use $timeout to simulate
//     * remote dataservice call.
//     */
//    function querySearchAC (query) {
//    	var result;
//      if(query) {
//        result = loadAndParseContactsAC().then(function(data) {
//             return data.filter(createFilterForAC(query))
//           })
//      } else {
//        result = []
//      }
//      return result
//      }
//    
//    function parseAC (data) {
//        return data.data.map(function (repo) {
//        	repo.value = repo.name.toLowerCase();
//            return repo;
//          })      
//      }
//    function loadAndParseContactsAC() {
//        return $http.get('http://localhost:8080/patient').then(parseAC)
//      }
//    /**
//     * Build `components` list of key/value pairs
//
//
//    /**
//     * Create filter function for a query string
//     */
//    function createFilterForAC(query) {
//      var lowercaseQuery = angular.lowercase(query);
//
//      return function filterFn(item) {
//        return (item.value.indexOf(lowercaseQuery) === 0);
//      };
//
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    
//    $scope.yourmodel=[];
//var self = this;
//    
//$scope.querySearch = querySearch;
//
//$scope.contacts = [];
//$scope.filterSelected = true;
//
//    /**
//     * Search for contacts.
//     */
//    function querySearch (query) {
//      var result;
//      if(query) {
//        result = loadAndParseContacts().then(function(data) {
//             return data.filter(createFilterFor(query))
//           })
//      } else {
//        result = []
//      }
//      return result
//    }
//
//    /**
//     * Create filter function for a query string
//     */
//    function createFilterFor(query) {
//      var lowercaseQuery = angular.lowercase(query);
//
//      return function filterFn(contact) {
//        return (contact._lowername.indexOf(lowercaseQuery) != -1);;
//      };
//
//    }
//    function parse (data) {
//      return data.data.map(function (c, index) {
//          var contact = {
//            name: c.name,
//            id: c.id,
//          };
//          contact._lowername = contact.name.toLowerCase();
//          return contact;
//        })      
//    }
//    function loadAndParseContacts() {
//      return $http.get('http://localhost:8080/medicine').then(parse)
//    }
//
});