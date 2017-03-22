var app = angular.module('myApp');

app.controller('medicineController', function(
        $scope, $interval, $location,medicineService, typeMedicineService,$http) {
	
	$scope.medicine = {
			name : "",
			typeId : {
				id: 0,
				typename:"",
			},
			producer : "",
			dosage : "",
			mfg : "",
		};

	$scope.typemedicine = {
			typename :"",
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
		bootbox.alert({
			message: "Could Not Find Any Data!",
			title: "MESSAGE",
		    size: 'small'
		});
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
		//$scope.medicine.typeId = $scope.medicine.object;
		medicineService.createMedicine($scope.medicine).then(createMedicineSuccess,createMedicineError);
	};
	var createMedicineSuccess = function(data) {
		bootbox.alert({
			message: "Add Medicine Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		
		$scope.getMedicine();
	};
	var createMedicineError = function(error) {
		bootbox.alert({
			message: "Add Medicine Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
/////-----------update Medicine-------------
	$scope.updateMedicine = function(id,medicine){
		//$scope.curentMedicine.typeId = $scope.medicine.typeId.id;
		medicineService.updateMedicine(id,medicine).then(updateMedicineSuccess,updateMedicineError);
	};
	var updateMedicineSuccess = function(data) {

//		alert('update medicine Success:' + data.name);
//		$(".modal").modal("hide");
		$scope.getMedicine();

		bootbox.alert({
			message: "Update Medicine Success!",
			title: "MESSAGE",
		    size: 'small'
		});

	};
	var updateMedicineError = function(error) {
		bootbox.alert({
			message: "Update Medicine Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
	/////-----------delete medicine-------------
	$scope.deleteMedicine= function(id){
		medicineService.deleteMedicine(id).then(deleteMedicineSuccess,deleteMedicineError);
		
	};
	var deleteMedicineSuccess = function(data) {
		bootbox.alert({
			message: "Delete Medicine Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getMedicine();
	};
	var deleteMedicineError = function(error) {
		bootbox.alert({
			message: "Delete Medicine Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
	
/////============get All Type Medicine===============
	$scope.getTypeMedicine = function(data){
		typeMedicineService.getTypeMedicine().then(getSuccess,getError);
	};
	var getSuccess = function(data) {
		$scope.typemedicines = data;
	};
	var getError = function(error) {
		bootbox.alert({
			message: "Could Not Find Any Data!",
			title: "MESSAGE",
		    size: 'small'
		});
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
		bootbox.alert({
			message: "Add New Type Medicine Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getTypeMedicine();
	};
	var createError = function(error) {
		bootbox.alert({
			message: "Add Type Treatment Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
/////-----------update Type Medicine-------------
	$scope.updateTypeMedicine = function(id,typeMedicine){
		typeMedicineService.updateTypeMedicine(id,typeMedicine).then(updateSuccess,updateError);
	};
	var updateSuccess = function(data) {
		bootbox.alert({
			message: "Update Type Medicine Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getTypeMedicine();
	};
	var updateError = function(error) {
		bootbox.alert({
			message: "Update Type Treatment Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
	
	/////-----------delete Type medicine-------------
	$scope.deleteTypeMedicine= function(id){
		typeMedicineService.deleteTypeMedicine(id).then(deleteSuccess,deleteError);
		
	};
	var deleteSuccess = function(data) {
		bootbox.alert({
			message: "Delete Type Medicine Success!",
			title: "MESSAGE",
		    size: 'small'
		});
		$scope.getTypeMedicine();
	};
	var deleteError = function(error) {
		bootbox.alert({
			message: "Delete Type Medicine Error!",
			title: "MESSAGE",
		    size: 'small'
		});
	};
});