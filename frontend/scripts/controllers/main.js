'use strict';

angular.module('neighbroTaxi')

    .controller('mainCtrl', function ($scope, $http) {


        $scope.showNavbar = function(){
            if(window.location.hash =='#/login' || window.location.hash =='#/registration'){
                return false;

            }else{
                return true;
            }
            return ['#/login', '#/registration'].indexOf(window.location.hash) > -1;
        }

        $scope.showIfUrl = function(actualUrl){
            if(window.location.hash ==actualUrl){
                return true;
            }else{
                return false;
            }
        }



    });
