'use strict';

angular.module('neighbroTaxi')
    .directive('login', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/login.html',
            replace : true,
            controller: 'mainCtrl'
        }
    });