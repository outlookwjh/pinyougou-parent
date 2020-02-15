app.controller('itemController',function ($scope) {

    $scope.addNum=function (x) {
        $scope.num=$scope.num+x;

        if($scope.num<=0){
            $scope.num=1;
        }
    }

    //定义一个变量，记录用户选择的规格
    $scope.specItems={};
    $scope.selectSpecItem=function (name,value) {
        $scope.specItems[name]=value;
        $scope.searchSku();
    }

    //判断某规格选项是否被用户选中,如果选中，页面展示选中的样式
    $scope.isSelectedSpec=function (name,value) {

        if ($scope.specItems[name]==value){
            return true;
        }else {
            return false;
        }

    }
    //页面加载时读取默认sku的title和price给页面显示
    $scope.loaddefaultsku=function(){
        $scope.sku=skuList[0];//skulist是页面生产的变量，为全局变量，不需要￥scope
        $scope.specItems=JSON.parse(JSON.stringify($scope.spec)); //深克隆，将对象转成json再转对象
    }

    //匹配规格
    $scope.mathObj=function (map1,map2) {

        for (var k in map1){
            if (map1[k] != map2[k]){
                return false;
            }
        }
        for (var k in map2){
            if (map2[k] != map1[k]){
                return false;
            }
        }
        return true;

    }

    //根据规格查找sku
    $scope.searchSku=function(){

        for (var i=0;i<skuList;i++){
            if (mathObj(skuList[i].spec,$scope.specItems)){
                $scope.sku=skuList[i];
                return;
            }
        }

    }

    //添加商品到购物车
    $scope.addToCart=function(){
        alert('skuid:'+$scope.sku.id);
    }

});