'use strict';

angular.module('neighbroTaxi', ['ngRoute']).config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.withCredentials = true;
}]);
