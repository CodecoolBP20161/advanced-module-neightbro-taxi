/**
 * Created by annakertesz on 2/17/17.
 */
'use strict';

angular.module('neighbroTaxi')
    .directive( 'goClick', function ( $location) {
        return function ( scope, element, attrs ) {
            var path;

            attrs.$observe( 'goClick', function (val) {
                path = val;
            });

            element.bind( 'click', function () {
                scope.$apply( function () {
                    $location.path( path );
                });
            });
        };
    });