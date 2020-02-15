app.service("contentService",function($http){
    //根据分类ID查询广告列表
    this.findPortalByCatId=function (id){
        return $http.get('content/findPortalByCatId.do?id='+id);
    }
});
