<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\6\23 0023
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>付款界面</title>

</head>
<body >
<div id="orderid"></div>
<br>请扫码支付
<img id="img"/>
<div id="resultInfo">等待支付中。。。</div>

<script type="text/javascript">
    <%--
     用户订单标签id: orderid 如: <div id="orderid"></div>
     二维码标签id: img 如: <img id="img"/>
     支付结果标签id: resultInfo 如: <div id="resultInfo">等待支付中。。。</div>
    --%>
    var orderid = document.getElementById("orderid");
    var img = document.getElementById("img");
    var resultInfo = document.getElementById("resultInfo");

    var thisURL=document.URL;
    var values=thisURL.split('?')[1];
    var orderidvalue=values.split("&")[0].split("=")[1];
    var urlvalue=values.split("&")[1].split("=")[1];

    var orderid1=unescape(orderidvalue);
    urlvalue=unescape(urlvalue);

    orderid.innerHTML=orderid1;
    img.src="image?url="+urlvalue;



    <!-- 轮询 -->
    function getXMLHTTPRequest(){
        var xmlhttp;
        if(window.XMLHttpRequest){
            xmlhttp=new XMLHttpRequest();
        }else{
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
    }
    var i=4;
    var int=self.setInterval("clock()",1000);

    function getorderstate(){
        var xmlhttp=getXMLHTTPRequest();
        xmlhttp.onreadystatechange=function(){
            var str=xmlhttp.responseText;

            if(str=="支付成功"){
                resultInfo.innerHTML=str+"。<label id='success'>5</label>秒后跳转订单页面";
            }else{
                resultInfo.innerHTML=str;
                setTimeout("getorderstate();",10000);
            }
        };
        var url="getorderstate";
        xmlhttp.open("post",url,true);
        xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send("orderid="+orderid1);
    }
    setTimeout("getorderstate();",10000);
    function gouserlist() {
        window.location.href="myAccount.jsp";
    }
    function clock()
    {
        var success=document.getElementById("success");
        success.innerHTML=i;
        if(i==0){
            window.location.href="shop/orderlist.jsp";
            int=window.clearInterval(int);
        }
        i--
    }
</script>

</body>
</html>
