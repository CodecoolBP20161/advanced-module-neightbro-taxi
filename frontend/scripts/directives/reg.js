'use strict';

angular.module('neighbroTaxi')
    .directive('reg', function () {
        return {
            templateUrl: 'templates/reg.html',
            replace : true,
            controller: 'mainCtrl',
            restrict: 'E'
        }
    });