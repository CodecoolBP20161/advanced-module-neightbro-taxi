/**
 * Created by annakertesz on 3/26/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive('menu', function () {
        return {
            restrict: 'E',
            templateUrl: 'templates/menu.html',
            replace : true,
            controller: 'menuCtrl'
        }
    });
