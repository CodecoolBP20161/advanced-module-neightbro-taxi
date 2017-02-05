'use strict';

angular.module('neighbroTaxi')

    .service('dataService', function ($http) {
        this.getUsers = function (cb) {
            $http.get('mock/users.json').then(cb);
        };
    })