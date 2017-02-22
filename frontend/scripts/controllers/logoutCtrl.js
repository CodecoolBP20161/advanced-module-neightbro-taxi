'use strict';

angular.module('neighbroTaxi')

.controller('logoutCtrl', function ($scope, $http, $location) {

    $scope.logout = function () {

        $scope.inUser = {};
        $http.post('http://localhost:9000/logout').
            success(function (response) {
                console.log(response);
                $location.path('/');
            }).error(function (response) {
                console.log(response);
        });

        console.log($scope.inUser);
        console.log("LOGGED OUT");
    }

})