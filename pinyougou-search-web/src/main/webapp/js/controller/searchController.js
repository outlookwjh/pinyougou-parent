 //控制层 
app.controller('searchController' ,function($scope ,searchService){

	$scope.itemSearch=function () {

		searchService.search($scope.searchMap).success(

			function (response) {

				$scope.result=response;

            }

		);

    }
});	
