/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive('navbar', function () {
        return {
            templateUrl: 'templates/navbar.html',
            controller: 'mainCtrl',
            replace : true,
            restrict: 'E'
        }
    });