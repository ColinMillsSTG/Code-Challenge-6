/**
 * Created by colin.mills on 6/28/2016.
 */
var app = angular.module('presentCrudOperations',[]);

app.directive("presentOperationTabs", function(){
    return{
        restrict: "E",
        templateUrl: "/presentOperationTabs.html",
        controller: function () {
            this.presentTab = 1;

            this.isSet = function(checkTab){
                return this.presentTab == checkTab;
            }

            this.setTab = function (activeTab) {
                this.presentTab = activeTab;
            }
        },
        controllerAs: "presentTab"
    }
});

app.directive("create", function(){
   return{
       restrict:"E",
       templateUrl: "presents/createPresents.html"
   }
});

app.directive("read", function(){
   return{
       restrict:"E",
       templateUrl: "presents/readPresents.html"
   }
});

app.directive("update", function(){
   return{
       restrict:"E",
       templateUrl: "presents/updatePresents.html"
   }
});

app.directive("delete", function(){
   return{
       restrict:"E",
       templateUrl: "presents/deletePresents.html"
   }
});