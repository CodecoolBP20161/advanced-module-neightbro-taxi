'use strict';

angular.module('neighbroTaxi')
    .directive('routes', function () {
        return {
            templateUrl: 'templates/routes.html',
            controller: 'routesCtrl',
            replace : true,
            restrict: 'E'
        }
    });