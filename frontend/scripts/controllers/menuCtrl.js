'use strict';

angular.module('neighbroTaxi')

.controller('menuCtrl', function ($scope) {

    $scope.showDropDown = true;

    $scope.showLogin = true;
    $scope.showReg = false;

    $scope.login = function () {
        $scope.showLogin = true;
        $scope.showReg = false;
    };

    $scope.reg = function () {
        $scope.style = false;
        $scope.showLogin = false;
        $scope.showReg = true;
        $scope.style = true;
    };

});