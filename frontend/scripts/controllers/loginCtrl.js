'use strict';

angular.module('neighbroTaxi')

.controller('loginCtrl', function ($scope, $http, responseService, $location) {

    $scope.user = {};
    $scope.answer;

    $scope.submitUser = function (valid) {
        console.log('FASZT');

        if(valid) {
            console.log('NOTFASZT');
            console.log($scope.user);

            $http.post('http://localhost:9000/user-login', $scope.user).
            success(function (data) {
                console.log(data);
            }).error(function (data) {
                console.log(data);
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