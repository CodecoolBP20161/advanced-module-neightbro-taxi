/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')

    .controller('registrationCtrl', function ($scope, $http, $location) {

        $scope.newuser = {};

        $scope.addUser = function (valid) {

            if(valid){
                console.log($scope.newuser);
                $http.post(properties.URL+'/registration', $scope.newuser).
                success(function (data) {
                    console.log("Success");
                    $scope.validForm.$setPristine();
                    $location.path( "/login" );

                    $scope.newuser = null;
                }).error(function (data) {
                    console.log(":(");
                });
            }
        }
    });