app.service("uploadService",function ($http) {
    
    this.upload=function () {

        var formdata=new FormData();

        formdata.append("file",file.files[0]);

        return $http({

            method:'post',
            url:'../upload.do',
            data:formdata,
            headers:{'Content-Type':undefined},
            transformRequest:angular.identity

        })

    }
    
    this.alertMsg=function () {
        return alert("hello");
    }
})