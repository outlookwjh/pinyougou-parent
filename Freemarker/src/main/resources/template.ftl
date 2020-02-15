<html>
<head>
    <meta charset="utf-8">
    <title>Freemarker入门小DEMO </title>
</head>
<body>
<#--我只是一个注释，我不会有任何输出  -->
${name},你好。${message}


<#--1. assign指令-->
    <#--定义一个简单变量-->
    <#assign linkMan="王建宏">

     ${linkMan} said this is a population word!
    <#--定义一个对象类型-->
    <#assign info={'sex':'female','age':'23'}>

    地址：${info.sex}
    年龄：${info.age}


<#--2. include指令-->
    <#include "head.ftl">


<#--3.if指令-->

    <#if success==true>  <#--success为代码中传递过来的值-->
        你已通过实名认证
    <#elseif success==false>
        你未通过实名认证
    </#if>

<#--4. list指令-->
    <#list goodsList as goods>
        ${goods_index+1} 商品名称：${goods.name} 商品价格：${goods.price}
    </#list>

<#--5. 内建函数-->
    <#--5.1 size-->
    共${goodsList?size}条记录
    <#--5.2 json串转对象-->
    <#assign json="{'bank':'工商银行','account':'123456789'}">
    <#assign obj=json?eval>
    开户行：${obj.bank} 银行账户：${obj.account}


    <#--5.3 日期格式化-->
    当前日期：${today?date} <br>
    当前时间：${today?time} <br>
    当前日期+时间：${today?datetime} <br>
    日期格式化：  ${today?string("yyyy年MM月")}

    <#--5.4 数字转字符串-->
    累计积分：${numbertostring}(数字格式)

            ${numbertostring?c}(string格式)
<#--6.空值处理-->

    <#--这个值代码中没有是空值：${hello}-->

    ${hello!'hi'}

    <#if hello??>
        true
    <#else>
        false
    </#if>

</body>
</html>
