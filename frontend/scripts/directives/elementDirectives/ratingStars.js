/**
 * Created by annakertesz on 2/18/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive('ratingStars', function () {
        return {
            templateUrl: 'templates/ratingStars.html',
            controller: 'ratingStarsCtrl',
            replace : true,
            restrict: 'E'
        }
    });