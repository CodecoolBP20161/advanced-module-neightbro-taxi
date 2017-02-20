'use strict';

angular.module('neighbroTaxi')

.controller('loginCtrl', function ($scope, $http, responseService, $location) {

    $scope.user = {};
    $scope.answer;

    $scope.submitUser = function (valid) {

        if(valid) {
            console.log($scope.user);

            $http.post('http://localhost:9000/user-login', $scope.user).
            success(function (data) {
                console.log(data.username);
                console.log(data.email);
            }).error(function (data) {
                console.log("fail");
            });

            responseService.getResponse(function (response) {
                var answer = response.data;
                $scope.answer = answer;
            });

            if($scope.answer == null) {
                console.log("PASSWORD prob");
            } else if ($scope.answer = "Successfully logged in!"){
                $location.path( "#/home" );
            } else {
                console.log("WUT");
            };
        }
    }
})