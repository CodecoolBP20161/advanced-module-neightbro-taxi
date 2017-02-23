'use strict';

angular.module('neighbroTaxi')

    .service('loggedUser', function ($http) {
        this.getUser = function () {
            $http.get('http://localhost:9000/logged-in-user')
                .success(function (response) {
                    $scope.inUser = response;
                    var inUser = $scope.inUser;
                    return inUser;
                }).error(function (response) {
                console.log(response);
            });
        };

    })