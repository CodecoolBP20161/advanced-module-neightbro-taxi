/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive('centralLogo', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/central_logo.html',
            replace : true,
            controller: 'mainCtrl'
        }
    });