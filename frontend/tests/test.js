/**
 * Created by annakertesz on 3/9/17.
 */
describe("Unit Testing Examples", function() {

    beforeEach(module('neighbroTaxi'));


    // var $controller;
    //
    // beforeEach(angular.mock.inject(function(_$controller_){
    //     $controller = _$controller_;
    // }));

    it('should exist', function() {
        expect(neighbroTaxi.loginCtrl).toBeDefined();
    });

});