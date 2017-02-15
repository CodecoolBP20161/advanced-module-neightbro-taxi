'use strict';

angular.module('neighbroTaxi')

    .service('responseService', function ($http) {
        this.getResponse = function (cb) {
            $http.get('http://localhost:9000/login').then(cb);
        };

    })