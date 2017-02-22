'use strict';

angular.module('neighbroTaxi')

.controller('logoutCtrl', function ($scope, $http, $location, $window) {

    $scope.logout = function () {

        console.log($scope.inUser)

        $scope.inUser = {};
        $window.localStorage.clear();
        $location.path('/');

        console.log($scope.inUser);
        console.log("LOGGED OUT");
    }

})