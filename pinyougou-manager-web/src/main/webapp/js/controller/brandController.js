app.controller('brandController',function ($scope,$controller,brandService){

    $controller('baseController',{$scope:$scope});

        //查询品牌列表
        $scope.findAll=function(){
            brandService.findAll().success(
                function(response){
                    $scope.list=response;
                }
            );
        }

        //搜索
        $scope.searchEntity={};
        $scope.search=function (pageNum,pageSize) {

            brandService.search(pageNum,pageSize,$scope.searchEntity).success(
                function (response) {
                    $scope.paginationConfig.totalItems=response.total;
                    $scope.list=response.rows;
                }
            );

        }

        //分页查询
        $scope.findPage=function (pageNum,pageSize) {

            brandService.findPage().success(

                function (response) {

                    $scope.list=response.rows;

                    $scope.paginationConfig.totalItems=response.total;
                }
            );
        }

        //添加更新品牌
        $scope.addBrand=function () {

            if ($scope.entity.id != null){
                //更新
                brandService.updateBrand($scope.entity).success(
                    function (response) {

                        if (response.success){

                            $scope.reloadList();

                        }else {
                            alert("update brand failure")
                        }


                    }
                );
            }else {
                //添加
                brandService.addBrand($scope.entity).success(
                    function (response) {

                        if(response.success)
                        {
                            $scope.reloadList();
                        }else {
                            alert(response.message);
                        }

                    }
                )
            }

        };

        //查询品牌
        $scope.findOne=function (id) {

            brandService.findOne(id).success(
                function (response) {

                    if(response.success){

                        $scope.entity=response.brand;
                    }
                }
            );
        }


        //批量删除
        $scope.batchDel=function () {

            brandService.batchDel($scope.selectedIds).success(
                function (response) {

                    if (response.success){
                        $scope.reloadList();
                    }else {
                        alert("delete failure");
                    }

                }
            );

        }



    }
    
);