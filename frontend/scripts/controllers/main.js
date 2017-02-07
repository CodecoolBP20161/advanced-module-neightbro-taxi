'use strict';

angular.module('neighbroTaxi')

    .controller('mainCtrl', function ($scope, $http) {
        /*
        dataService.getUsers(function (response) {
            var users = response.data;
            $scope.users = users;
        });
        */

        $scope.newuser = {};

        $scope.addUser = function (valid) {

            if(valid){
                console.log($scope.newuser);

                $http.post('http://localhost:9000/registration', $scope.newuser).
                success(function (data) {
                    console.log("Success");
                }).error(function (data) {
                    console.log(":(");
                });
                $scope.showLogin = true;
                $scope.showReg = false;
            }
        }

        $scope.resetForm = function (newuser) {
            $scope.newuser = null;
            $scope.myForm.$setPristine();
        };

        /*$scope.addUser = function () {
            $scope.users.push({name: name, email: email, password: password, passwordconfig: passwordconfig});
        };*/
    });
