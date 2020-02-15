app.controller('baseController' ,function($scope){

    //分页控件配置
    $scope.paginationConfig={
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            $scope.reloadList();//重新加载
        }
    }


    //重新加载
    $scope.reloadList=function () {
        $scope.search($scope.paginationConfig.currentPage,$scope.paginationConfig.itemsPerPage);

    }

    //复选框
    $scope.selectedIds=[];
    //更新复选框
    $scope.updateSelectedIds=function ($event,id) {

        if($event.target.checked){
            $scope.selectedIds.push(id);
        }else {
            var index=$scope.selectedIds.indexOf(id);

            $scope.selectedIds.splice(index,1);
        }
    }

    //将json串中某一个值拿出来拼接
    $scope.jsonToObj=function (jsonStr,key) {

        var obj = JSON.parse(jsonStr);

        var value="";

        for (var i=0;i<obj.length;i++){

            if (i>0){
                value+=",";
            }

            value+=obj[i][key];

        }

        return value;
    }

});