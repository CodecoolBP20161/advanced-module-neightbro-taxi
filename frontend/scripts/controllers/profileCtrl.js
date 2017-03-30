'use strict'

angular.module('neighbroTaxi')

.controller('profileCtrl', function ($scope, $http) {

    $http.get(properties.URL+'/logged-in-user')
        .success(function (response) {
            $scope.inUser = response;
            var inUser = $scope.inUser;
            console.log(inUser);
        }).error(function (response) {
            console.log(response);
    });

})