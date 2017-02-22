'use strict';

angular.module('neighbroTaxi')

.controller('logoutCtrl', function ($scope, $http, $location) {

    $scope.logout = function () {

        $scope.inUser = {};
        $http.post('http://localhost:9000/user-logout').
            success(function (response) {
                console.log(response);
                $location.path('/');

                console.log($scope.inUser);
                console.log("LOGGED OUT");
            }).error(function (response) {
                console.log(response);
        });
    }

})