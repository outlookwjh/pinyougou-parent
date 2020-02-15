app.service("brandService",function ($http) {

    //查询品牌列表
    this.findAll=function(){
       return $http.get('../brand/findAll.do');
    }

    //搜索
    this.search=function(pageNum,pageSize,searchEntity) {
        return $http.post("../brand/findPage.do?pageNum="+pageNum+"&pageSize="+pageSize,searchEntity);
    }

    //分页查询
    this.findPage=function (pageNum,pageSize) {

        return $http.get("../brand/findPage.do?pageNum=" + pageNum + "&pageSize=" + pageSize);
    }

    //更新品牌
    this.updateBrand=function (entity) {
        return $http.post("../brand/modifyBrand.do", entity)
    }

    //添加品牌
    this.addBrand=function (entity) {
        return $http.post("../brand/insertBrand.do",entity)
    }

    this.findOne=function (id) {

       return $http.get("../brand/findBrandById.do?id="+id);
    }

    this.batchDel=function (selectedIds) {

       return $http.get("../brand/batchDel.do?ids="+selectedIds);
    }

});