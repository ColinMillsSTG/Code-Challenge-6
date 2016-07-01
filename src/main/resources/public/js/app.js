/**
 * Created by colin.mills on 6/27/2016.
 */
(function() {
    var app = angular.module('taskManagerApp', ['wishlistCrudOperations']);

    app.controller('wishlistController', function ($scope, $http) {

        $scope.wishlist = [];
        $scope.presents = [];
        $scope.testWishlist =
            '[ {"name": "Mini Puzzle", "size": "small", "clatters": "yes", "weight": "light"}, ' +
            '{"name": "Toy Car", "size": "medium", "clatters": "a bit", "weight": "medium"},' +
            ' {"name": "Card Game", "size": "small", "clatters": "no", "weight": "light"} ]"';

        $http.defaults.headers.post["Content-Type"] = "application/json";

        $scope.getWishList = function () {
            $http.get('/wishlist/').success(function (data) {
                wishlist = data;
            });
        };

        $scope.setTestWishlistData = function () {
            $http({
                method: 'POST',
                url: '/wishlist/',
                data: testWishlist,
                headers: {
                    'Content-Type': 'application/JSON'
                }
            });

        };
    });

})();