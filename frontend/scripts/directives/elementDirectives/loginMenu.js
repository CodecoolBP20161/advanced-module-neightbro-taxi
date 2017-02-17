/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive('loginMenu', function () {
        return {
            templateUrl: 'templates/loginMenu.html',
            controller: 'mainCtrl',
            replace : true,
            restrict: 'E'
        }
    });