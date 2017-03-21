var app = angular.module('myApp', [ 'ngRoute', 'ngResource',
		'angularUtils.directives.dirPagination','ngMaterial' ]);
app.config(function($routeProvider) {
	$routeProvider.when('/patient', {
		templateUrl : '/views/patient.html',
		controller : 'PatientController'
	}).when('/', {
		templateUrl : '/views/homepage.html',
		controller : 'HomeController'
	}).when('/patient', {
		templateUrl : '/views/patient.html',
		controller : 'patientController'
	}).when("/medicine", {
		templateUrl : "/views/medicine.html",
		controller : "medicineController"
	}).when('/usersList', {
		templateUrl : '/views/usersList.html',
		controller : "userController"
	}).when('/userProfile', {
		templateUrl : '/views/userProfile.html',
		controller : "userController"
	}).when('/test', {
		templateUrl : '/views/TestContactList.html',
		controller : "contactlistController"
	}).when('/treatment', {
		templateUrl : '/views/treatment.html',
		controller : "treatmentController"
	})

	.when('/treatment/:treatmentId', {
		templateUrl : '/views/treatmentdetail.html',
		controller : "treatmentDetailController"
	});

	/*
	 * .otherwise( { redirectTo: '/'} );
	 */
});
