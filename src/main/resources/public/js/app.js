/**
 * Created by colin.mills on 6/27/2016.
 */
(function() {
    var app = angular.module('taskManagerApp', ['wishlistCrudOperations','presentCrudOperations']);

    app.controller('wishlistController', function ($scope, $http) {

        $scope.presents = [];
        $scope.newPresents = [];

        $scope.wishlist = [];
        $scope.newWishlist = [];

        $http.defaults.headers.post["Content-Type"] = "application/json";

        $scope.newWishItem = function () {
            var newIndex;
            if($scope.wishlist.length > 0){
                newIndex = $scope.wishlist[$scope.wishlist.length].id + $scope.newWishlist.length + 1;
            }else{
                newIndex = $scope.newWishlist.length + 1;
            }
            $scope.newWishlist.push({"id": newIndex, "name": "", "size": "", "clatters": "", "weight": "", "giver": ""});
        };

        $scope.newPresent = function () {
            var newIndex;
            if($scope.presents.length > 0){
                newIndex = $scope.presents[$scope.presents.length].id + $scope.newPresents.length + 1;
            }else{
                newIndex = $scope.newPresents.length + 1;
            }
            $scope.newPresents.push({"id": newIndex, "size": "", "clatters": "", "weight": "", "giver": ""});
        };

        //Wishlist methods
        $scope.createWishlist = function () {
            $http({
                method: 'POST',
                url: '/api/wishlist/',
                data: $scope.newWishlist
            });

            $scope.wishlist.push($scope.newWishlist);

            $scope.newWishlist = [];
        };

        $scope.getWishlist = function () {
            $http.get('/api/wishlist/').success(function (response) {
                if(response.length > 0) {
                    $scope.wishlist = response;
                }
            });
        };

        $scope.updateWishlist = function (wishItem) {
            $http({
                method: 'POST',
                url: '/api/wishlist/' + wishItem.id,
                data: wishItem,
                headers: {
                    'Content-Type': 'application/JSON'
                }
            });
        };

        $scope.deleteWishlist = function(wishItem){
            $http({
                method: 'DELETE',
                url: '/api/wishlist/' + wishItem.id
            });

            $scope.wishlist.splice($scope.wishlist.indexOf(wishItem), 1);
        };

//Present methods
        $scope.createPresents = function () {
            $http({
                method: 'POST',
                url: '/api/presents/',
                data: $scope.newPresents
            });

            $scope.presents.push($scope.newPresents);

            $scope.newPresents = [];
        };

        $scope.getPresents = function () {
            $http.get('/api/presents/').success(function (response) {
                if(response.length > 0) {
                    $scope.presents = response;
                }
            });
        };

        $scope.updatePresents = function (present) {
            $http({
                method: 'POST',
                url: '/api/presents/' + present.id,
                data: present,
                headers: {
                    'Content-Type': 'application/JSON'
                }
            });
        };

        $scope.deletePresents = function(present){
            $http({
                method: 'DELETE',
                url: '/api/wishlist/' + present.id
            });

            $scope.presents.splice($scope.presents.indexOf(present), 1);
        };
    });

})();