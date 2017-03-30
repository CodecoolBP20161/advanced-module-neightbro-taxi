'use strict';

angular.module('neighbroTaxi')

.controller('logoutCtrl', function ($scope, $http, $location) {

    $scope.logout = function () {

        $scope.inUser = {};
        $http.post(properties.URL+'/user-logout').
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