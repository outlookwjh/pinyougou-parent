 //控制层 
app.controller('goodsController' ,function($scope,$controller ,goodsService,uploadService,itemCatService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存
	$scope.add=function () {

		   var vemp=editor.html();
        $scope.entity.goodsDesc.introduction=vemp;

		//alert($scope.entity.goodsDesc.introdunction+"hee");
		goodsService.add($scope.entity).success(
			function (response) {

				if(response.success){
					$scope.entity={};
					alert(response.message);
					editor.html('');
				}else {
					alert(response.message);
				}


            }
		);

    }


	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectedIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectedIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConfig.totalItems=response.total;//更新总记录数
			}			
		);
	}

	//上传图片
	$scope.uploadfile=function(){
		//uploadService.alertMsg();
		uploadService.upload().success(
			function (response) {
				if (response.success){

                    $scope.img_entity.url=response.message;
                }else {
					alert(response.message);
				}

            }
		);

    }

    //添加图片
    $scope.entity={goods:{},goodsDesc:{itemImages:[]}}
    $scope.add_img_entity=function () {

		$scope.entity.goodsDesc.itemImages.push($scope.img_entity)
    }
    //移除图片
	$scope.del_img_entity=function (index) {
		$scope.entity.goodsDesc.itemImages.splice(index,1);
    }

    //商品分类列表
	$scope.searchItemCatList=function () {

		itemCatService.findByParentId(0).success(
			function (response) {
				$scope.itemCatL1=response;
            }
		);
    }

    //二级分类
	$scope.$watch('entity.goods.category1Id',function (newValue,oldValue) {

		itemCatService.findByParentId(newValue).success(
			function (response) {
				$scope.itemCatL2=response;
            }
		);

    })
	//三级分类
	$scope.$watch('entity.goods.category2Id',function (newValue,oldValue) {
		itemCatService.findByParentId(newValue).success(
			function (response) {
				$scope.itemCatL3=response;
            }
		);
    })

	//模板id
	$scope.$watch('entity.goods.category3Id',function (newValue,oldValue) {
		itemCatService.findOne(newValue).success(
			function (response) {
				$scope.entity.goods.typeTemplateId=response.typeId;

            }
		);
    })

	//品牌列表
    $scope.$watch('entity.goods.typeTemplateId',function (newValue,oldValue) {
        typeTemplateService.findOne(newValue).success(
            function (response) {
                $scope.typeTemplate=response;
                $scope.typeTemplate.brandIds=JSON.parse($scope.typeTemplate.brandIds);
				$scope.entity.goodsDesc.customAttributeItems=JSON.parse($scope.typeTemplate.customAttributeItems);
            }
        );
        typeTemplateService.findSpecList(newValue).success(
        	function (response) {
				$scope.specList=response;
            }
		);
    })

});	
