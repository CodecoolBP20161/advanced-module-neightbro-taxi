'use strict';

angular.module('neighbroTaxi')
    .directive('footerDir', function () {
        return{
            templateUrl: 'templates/footer.html',
            controller: 'mainCtrl',
            replace: true,
            restrict: 'E'
        }
    });