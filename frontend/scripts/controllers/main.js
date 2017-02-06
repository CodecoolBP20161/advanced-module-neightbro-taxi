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

        $scope.addUser = function () {
            console.log($scope.newuser);

            $http.post('http://localhost:9000/registration', $scope.newuser).
                success(function (data) {
                console.log(":)");
            }).error(function (data) {
                console.log(":(");
            });

            //$scope.users.push($scope.newuser);
        }

        $scope.resetForm = function (newuser) {
            $scope.newuser = null;
            $scope.myForm.$setPristine();
        };

        /*$scope.addUser = function () {
            $scope.users.push({name: name, email: email, password: password, passwordconfig: passwordconfig});
        };*/
    });
