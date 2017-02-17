'use strict';

angular.module('neighbroTaxi')
    .directive('reg', function () {
        return {
            templateUrl: 'templates/registration.html',
            replace : true,
            controller: 'mainCtrl',
            restrict: 'E'
        }
    });