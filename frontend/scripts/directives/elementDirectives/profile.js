/**
 * Created by annakertesz on 2/18/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive('profile', function () {
        return {
            templateUrl: 'templates/profile.html',
            controller: 'profileCtrl',
            replace : true,
            restrict: 'E'
        }
    });