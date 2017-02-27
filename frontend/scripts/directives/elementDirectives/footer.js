'use strict';

angular.module('neighbroTaxi')
    .directive('footer', function () {
        return {
            templateUrl: 'templates/footer.html',
            replace : true,
            scope: true,
            transclude: false,
            restrict: 'A'
        }
    });