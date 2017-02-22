'use strict';

angular.module('neighbroTaxi')

.controller('loginCtrl', function ($scope, $http, $location) {

    $scope.user = {};

    $scope.submitUser = function (valid) {

        if(valid) {
            console.log($scope.user);

            $http.post('http://localhost:9000/user-login', $scope.user).
            success(function (data) {
                console.log(data);

                if(data.errorMessages.length == 0){
                    console.log(data.infoMessages[0]);
                    console.log("Hello " + data.loggedInUser.name);
                    $location.path("/profile");

                    $http.get('http://localhost:9000/logged-in-user')
                        .success(function (response) {
                            $scope.inUser = response;
                            var inUser = $scope.inUser;
                            console.log(inUser);
                        }).error(function (response) {
                            console.log(response);
                    });

                }else{
                    $scope.errorM = data.errorMessages[0];
                    console.log($scope.errorM);
                }
            }).error(function (data) {
                console.log("fail");
            });

            // responseService.getResponse(function (response) {
            //     var answer = response.data;
            //     $scope.answer = answer;
            // });
            //
            // console.log($scope.answer);

        }
    }
})