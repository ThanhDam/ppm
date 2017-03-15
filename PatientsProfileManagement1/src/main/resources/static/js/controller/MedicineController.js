var app = angular.module('myApp');

app.controller('medicineController', function(
        $scope, $interval, $location,medicineService, typeMedicineService,$http) {
	
	$scope.medicine = {
			name : "",
			typeId : "",
			producer : "",
			dosage : "",
			mfg : "",
		};
	
	$scope.typemedicine = {
			typeName :"",
	};
	
	$http.get("http://localhost:8080/typemedicine").then(function(response) {
		$scope.typemedicines = response.data;
    });
	
	$http.get("http://localhost:8080/medicine").then(function(response) {
		$scope.medicines = response.data;
		angular.forEach($scope.medicines, function(value, key){
			$http.get("http://localhost:8080/typemedicine/" + value.typeId)
  			.then(function(response){
				value.typename = response.data.typeName;
			});
	         });
    });

/////------------get All Medicine
	$scope.getMedicine = function(data){
		medicineService.getMedicine().then(getSuccess,getError);
	}
	var getSuccess = function(data) {
		$scope.medicines = data;
	};
	var getError = function(error) {
		$scope.error = "Could not find any data"
	};
	
/////----------get one medicine-------------
	$scope.getOne = function(id){
		//$scope.medicine.typeId = 
		medicineService.getOneMedicine(id).then(getOneSuccess,getOneError)
	};
	var getOneSuccess = function(data) {
		$http.get("http://localhost:8080/typemedicine/" + data.typeId)
			.then(function(response){
			data.typename = response.data.typeName;
		});
		$scope.curentMedicine = data;
		$scope.mfg = $filter('date')(new Date($scope.curentMedicine.dob), 'yyyy-MM-dd')
	};
	var getOneError = function(error) {
	};
	
/////-----------create Medicine ------------
	$scope.createMedicine = function(){
		$scope.medicine.typeId = $scope.medicine.type.typeId;
		medicineService.createMedicine($scope.medicine).then(createSuccess,createError);
	}
	var createSuccess = function(data) {
		alert('add new medicine Success:' + data.name);
		$scope.getMedicine();
	};
	var createError = function(error) {
	};
	
/////-----------update Medicine-------------
	$scope.updateMedicine = function(id,medicine){
		medicineService.updateMedicine(id,medicine).then(updateSuccess,updateError);
	};
	var updateSuccess = function(data) {
		alert('update medicine Success:' + data.name);
		$scope.getMedicine();
	};
	var updateError = function(error) {
	};
	
	/////-----------delete medicine-------------
	$scope.deleteMedicine= function(id){
		medicineService.deleteMedicine(id).then(deleteSuccess,deleteError);
		
	};
	var deleteSuccess = function(data) {
		alert('delete medicine Success:' + data.name);
		$scope.getMedicine();
	};
	var deleteError = function(error) {
	};
	
/////------------get All Type Medicine
	$scope.getTypeMedicine = function(data){
		typeMedicineService.getTypeMedicine().then(getSuccess,getError);
	}
	var getSuccess = function(data) {
		$scope.typemedicines = data;
	};
	var getError = function(error) {
		$scope.error = "Could not find any data"
	};

/////----------get one type medicine-------------
	$scope.getTypeOne = function(id){
		typeMedicineService.getOneTypeMedicine(id).then(getOneTypeSuccess,getOneTypeError)
	};
	var getOneTypeSuccess = function(data) {
		$scope.curentTypeMedicine = data;
	};
	var getOneTypeError = function(error) {
	};
/////-----------create Type Medicine ------------
	$scope.createTypeMedicine = function(){
		typeMedicineService.createTypeMedicine($scope.typemedicine).then(createSuccess,createError);
	}
	var createSuccess = function(data) {
		alert('add new type medicine Success:' + data);
		$scope.getTypeMedicine();
	};
	var createError = function(error) {
	};
	
/////-----------update Type Medicine-------------
	$scope.updateTypeMedicine = function(id,typeMedicine){
		typeMedicineService.updateTypeMedicine(id,typeMedicine).then(updateSuccess,updateError);
	};
	var updateSuccess = function(data) {
		alert('update type medicine Success:' + data.typeName);
		$scope.getTypeMedicine();
	};
	var updateError = function(error) {
	};
	
	/////-----------delete Type medicine-------------
	$scope.deleteTypeMedicine= function(id){
		typeMedicineService.deleteTypeMedicine(id).then(deleteSuccess,deleteError);
		
	};
	var deleteSuccess = function(data) {
		alert('delete type medicine Success:' + data.typeName);
		$scope.getTypeMedicine();
	};
	var deleteError = function(error) {
	};
});