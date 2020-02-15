app.controller("contentController",function($scope,contentService){
    $scope.adList=[];

    $scope.findPortalByCatId=function (id) {

        contentService.findPortalByCatId(id).success(
            function (response) {

                $scope.adList[id]=response;

            }
        );

    }
});
