'use strict';

angular.module('neighbroTaxi')

.controller('profileCtrl', function ($scope, $http) {

    $scope.onEdit = false;

    // $scope.inUser.phone="454-1234-345";
    // $scope.inUser.car="Ford fiesta, 5 hely";

    $scope.changeEditing = function() {
        $scope.onEdit = !$scope.onEdit;
    };

    $http.get('http://localhost:9000/logged-in-user')
        .success(function (response) {
            $scope.inUser = response;
            var inUser = $scope.inUser;
            console.log(inUser);
        }).error(function (response) {
            console.log(response);
    });

    $scope.editUser = function () {


        console.log($scope.inUser);
        $http.put('http://localhost:9000/update-user', $scope.inUser).
        success(function (data) {
            console.log("Success");
        }).error(function (data) {
            console.log(":(");
        });
        $scope.changeEditing();
    }

});