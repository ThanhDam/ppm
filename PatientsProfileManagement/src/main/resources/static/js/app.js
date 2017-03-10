var app = angular.module('myApp', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
	    /*.when('/another',{
	        templateUrl: '/patientprofile/views/another.html',
	        controller: 'AnotherController'
	    })*/
	    .when('/',{
	        templateUrl: '/views/homepage.html',
	        controller: 'HomeController'
	    });
    	
        /*.otherwise(
            { redirectTo: '/'}
        );*/
});
