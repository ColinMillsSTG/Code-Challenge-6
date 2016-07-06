/**
 * Created by colin.mills on 6/28/2016.
 */
var app = angular.module('wishlistCrudOperations',[]);

app.directive("wishlistOperationTabs", function(){
    return{
        restrict: "E",
        templateUrl: "/wishlistOperationTabs.html",
        controller: function () {
            this.wishlistTab = 1;

            this.isSet = function(checkTab){
                return this.wishlistTab == checkTab;
            }

            this.setTab = function (activeTab) {
                this.wishlistTab = activeTab;
            }
        },
        controllerAs: "wishlistTab"
    }
});

app.directive("createWishlist", function(){
   return{
       restrict:"E",
       templateUrl: "wishlist/createWishlist.html"
   }
});

app.directive("readWishlist", function(){
   return{
       restrict:"E",
       templateUrl: "wishlist/readWishlist.html"
   }
});

app.directive("updateWishlist", function(){
   return{
       restrict:"E",
       templateUrl: "wishlist/updateWishlist.html"
   }
});

app.directive("deleteWishlist", function(){
   return{
       restrict:"E",
       templateUrl: "wishlist/deleteWishlist.html"
   }
});