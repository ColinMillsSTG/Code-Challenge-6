/**
 * Created by colin.mills on 6/28/2016.
 */
var app = angular.module('wishlistCrudOperations',[]);

app.directive("wishlistOperationTabs", function(){
    return{
        restrict: "E",
        templateUrl: "/wishlistOperationTabs.html",
        controller: function () {
            this.tab = 1;

            this.isSet = function(checkTab){
                return this.tab == checkTab;
            }

            this.setTab = function (activeTab) {
                this.tab = activeTab;
            }
        },
        controllerAs: "tab"
    }
});

app.directive("create", function(){
   return{
       restrict:"E",
       templateUrl: "createWishlist"
   }
});

app.directive("read", function(){
   return{
       restrict:"E",
       templateUrl: "readWishlist"
   }
});

app.directive("update", function(){
   return{
       restrict:"E",
       templateUrl: "updateWishlist"
   }
});

app.directive("delete", function(){
   return{
       restrict:"E",
       templateUrl: "deleteWishlist"
   }
});