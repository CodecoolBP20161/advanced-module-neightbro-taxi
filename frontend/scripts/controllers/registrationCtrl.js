/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')

    .controller('registrationCtrl', function ($scope, $http, $location) {

        $scope.newuser = {};

        $scope.validPw = function(){
            if($scope.newuser.password == $scope.newuser.passwordConfirm){
                return false;
            }else{
                return true;
            }
        }

        $scope.addUser = function (valid) {

            if(valid){
                console.log($scope.newuser);
                $http.post('http://localhost:9000/registration', $scope.newuser).
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