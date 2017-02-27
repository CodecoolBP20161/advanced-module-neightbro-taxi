'use strict';

angular.module('neighbroTaxi')

.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/login'
            // templateUrl: 'home.html'
        })
        .when('/profile', {
            templateUrl: 'home.html'
        })
        .when('/login', {
            templateUrl: 'loginFrame.html'
        })
        .when('/registration', {
            templateUrl: 'loginFrame.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});