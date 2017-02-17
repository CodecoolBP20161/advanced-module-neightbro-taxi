/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')

    .controller('registrationCtrl', function ($scope, $http) {

        $scope.newuser = {};
        $scope.errorConfirm = false;

        $scope.addUser = function (valid) {

            if(valid){
                console.log($scope.newuser);
                $http.post('http://localhost:9000/registration', $scope.newuser).
                success(function (data) {
                    console.log("Success");
                }).error(function (data) {
                    console.log(":(");
                });

                $scope.validForm.$setPristine();
                $location.path( "/login" );
            }
            $scope.newuser = null;
        }
    });