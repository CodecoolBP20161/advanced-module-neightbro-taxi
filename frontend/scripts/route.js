'use strict';

angular.module('neighbroTaxi')

.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'welcome.html'
        })
        .when('/home', {
            templateUrl: 'home.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});