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
	
	$scope.typemedicines = [];
	
	$scope.sort = function(keyname){
		$scope.sortKey = keyname;
		$scope.reverse = !$scope.reverse;
	}
	
	
	$http.get("http://localhost:8080/typemedicine").then(function(response) {
		$scope.typemedicines = response.data;
    });
	$http.get("http://localhost:8080/medicine").then(function(response) {
		$scope.medicines = response.data;
    });

/////------------get All Medicine
	$scope.getMedicine = function(data){
		medicineService.getMedicine().then(getMedicineSuccess,getMedicineError);
	};
	var getMedicineSuccess = function(data) {
		$scope.medicines = data;   
	};
	var getMedicineError = function(error) {
		$scope.error = "Could not find any data"
	};
	
/////----------get one medicine-------------
	$scope.getOne = function(id){
		//$scope.medicine.typeId = 
		medicineService.getOneMedicine(id).then(getOneMedicineSuccess,getOneMedicineError)
	};
	var getOneMedicineSuccess = function(data) {

		$scope.curentMedicine = data;
		$scope.curentMedicine.mfg = new Date(data.mfg);
			
		
	};
	var getOneMedicineError = function(error) {
	};
	
/////-----------create Medicine ------------
	$scope.createMedicine = function(){
		$scope.medicine.typeId = $scope.medicine.type.typeId;
		medicineService.createMedicine($scope.medicine).then(createMedicineSuccess,createMedicineError);
	};
	var createMedicineSuccess = function(data) {
		alert('add new medicine Success:' + data.name);
		
		$scope.getMedicine();
	};
	var createMedicineError = function(error) {
	};
	
/////-----------update Medicine-------------
	$scope.updateMedicine = function(id,medicine){
		$scope.curentMedicine.typeId = $scope.medicine.typeId.id;
		medicineService.updateMedicine(id,medicine).then(updateMedicineSuccess,updateMedicineError);
	};
	var updateMedicineSuccess = function(data) {
		alert('update medicine Success:' + data.name);
		$scope.getMedicine();
	};
	var updateMedicineError = function(error) {
	};
	
	/////-----------delete medicine-------------
	$scope.deleteMedicine= function(id){
		medicineService.deleteMedicine(id).then(deleteMedicineSuccess,deleteMedicineError);
		
	};
	var deleteMedicineSuccess = function(data) {
		alert('delete medicine Success:' + data.name);
		$scope.getMedicine();
	};
	var deleteMedicineError = function(error) {
	};
	
/////------------get All Type Medicine
	$scope.getTypeMedicine = function(data){
		typeMedicineService.getTypeMedicine().then(getSuccess,getError);
	};
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
	};
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