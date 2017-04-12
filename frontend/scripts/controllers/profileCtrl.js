'use strict';

angular.module('neighbroTaxi')

    .controller('profileCtrl', function ($scope, $http) {

        $scope.onEdit = false;

        // $scope.inUser.phoneNumber="454-1234-345";
        // $scope.inUser.car="Ford fiesta, 5 hely";

        $scope.changeEditing = function () {
            $scope.onEdit = !$scope.onEdit;
        };


        $http.get(properties.URL + '/logged-in-user')
            .success(function (response) {
                $scope.inUser = response;
                var inUser = $scope.inUser;
                console.log(inUser);
            }).error(function (response) {
            console.log(response);
        });

        $scope.editUser = function () {
            console.log($scope.inUser);

            $http.post(properties.URL + '/update-user', $scope.inUser
                // {headers : {
                //     'Content-Type':   'application/x-www-form-urlencoded'}
                //     // 'Content-Type':   'application/json'}
                // }
            )
                .success(function (response) {
                    console.log("Success");
                }).error(function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status);
                console.log(thrownError);
            });
            $scope.changeEditing();


        }
    });