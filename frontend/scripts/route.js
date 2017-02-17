'use strict';

angular.module('neighbroTaxi')

.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/login'
            // templateUrl: 'home.html'
        })
        .when('/home', {
            templateUrl: 'home.html'
        })
        .when('/login', {
            templateUrl: 'login.html'
        })
        .when('/registration', {
            templateUrl: 'registration.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});