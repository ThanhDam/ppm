


var app = angular.module('myApp');
app.controller('treatmentController', function(
        $scope, $interval, $location,$http,patientService,userService,treatmentService,treatmentDetailService,allergicService) {
	
	$scope.treatment ={
			date : null,
			file : null,
			prescription : null,
			doctorId : {
					
			},
			patientId : {
					
			}
	}
	
////////////////autocomplete for patient
	
	$scope.searchText = null;
	$scope.selectedPatient = null;
	$scope.querySearchAC   = querySearchAC;

    function querySearchAC (query) {
    	var result;
      if(query) {
        result = loadAndParseAC().then(function(data) {
             return data.filter(createFilterForAC(query))
           })
      } else {
        result = []
      }
      return result
      }
    
    function parseAC (data) {
        return data.data.map(function (repo) {
        	repo.value = repo.name.toLowerCase();
            return repo;
          })      
      }
    
    function loadAndParseAC() {
        return $http.get('http://localhost:8080/patient').then(parseAC)
      }
    
    function createFilterForAC(query) {
      var lowercaseQuery = angular.lowercase(query);

      return function filterFn(item) {
        return (item.value.indexOf(lowercaseQuery)  != -1);
      };

    }
	
 ///////////////contacts chip for medicine and allergi
    
    
 //////////////////////////////////////////////////////////////////////
	
////==========Get All Treatment=======================================
	$http.get("http://localhost:8080/treatment").then(function(response) {
		$scope.treatments = response.data;
	});
	
	$scope.getAllTreatment= function(){
		treatmentService.getAllTreatment().then(getAllSuccess,getAllError)
	}
	var getAllSuccess = function(data) {
		$scope.treatments = data;
	};
	var getAllError = function(error) {
	};
	
////==========Get One Patient=======================================
	$scope.getOnePatient = function(id){
		patientService.getOnePatient(id).then(getOneSuccess,getOneError)
	};
	
	var getOneSuccess = function(data) {
		$scope.patient = data;
	};
	var getOneError = function(error) {
	};
	
////==========Get current doctor=======================================
	$http.get("http://localhost:8080/userProfile").then(function(response) {
		$scope.doctor = response.data;
	});
	
	
	$scope.getLoginUser = function(id){
		userService.getLoginUser().then(getLoginUserSuccess,getLoginUserError)
	};
	
	var getLoginUserSuccess = function(data) {
		$scope.user = data;
	};
	var getLoginUserError = function(error) {
	};
	
////==========Create treatment=========================================
	$scope.createTreatment = function(){
		$scope.treatment.patientId = $scope.selectedPatient.id;
		$scope.treatment.doctorId = $scope.doctor.id;
		$scope.treatment.date = new Date();
		treatmentService.createTreatment($scope.treatment).then(createTreatmentSuccess,createTreatmentError);
	};
	var createTreatmentSuccess = function(data) {
		bootbox.alert({
			message: "Add New Treatment Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getAllTreatment();
	};
	var createTreatmentError = function(error) {
		bootbox.alert({
			message: "Add Treatment Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
////==========Create treatment detail==================================
	$scope.createTreatmentDetail = function(){
		if($scope.medicines!=null){
			angular.forEach($scope.medicines, function(value, key){
				$scope.treatmentDetail.treatmentId = $scope.treatment.id;
				$scope.treatmentDetail.medicineId =value;
				$scope.treatmentDetail.diseases =$scope.treatment.prescription;
				treatmentDetailService.createTreatmentDetail($scope.treatmentDetail)
				
			});
		}
		else{
		}
	}
////==========Get One Treatment ==================================
	$scope.getOneTreatment = function(id){
		treatmentService.getOneTreatment(id).then(getOneSuccess,getOneError)
	};
	var getOneSuccess = function(data) {
		$scope.currentTreatment = data;
		$scope.currentTreatment.date = new Date();
	};
	var getOneError = function(error) {
	};
////==========Update Treatment ==================================
	$scope.updateTreatment = function(id,treatment){
		/*if($scope.currentTreatment.doctorId.id==$scope.doctor.id){*/
			treatmentService.updateTreatment(id,treatment).then(updateSuccess,updateError);
/*		}
		else{
			alert('You have not permission');
		}*/
	};
	var updateSuccess = function(data) {
		bootbox.alert({
			message: "Update Treatment Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		//$(".modal").modal("hide");
		$scope.getAllTreatment();
		
	};
	var updateError = function(error) {
		bootbox.alert({
			message: "Update Treatment Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
		
		
	
	/*$scope.updateTreatment = function(id,treatment){
		treatmentService.updateTreatment(id,treatment).then(updateSuccess,updateError)
	};
	var updateSuccess = function(data) {
		alert('update treatment Success:' + data.name);
		$scope.getAllTreatment();
		
	};*/
	
});
