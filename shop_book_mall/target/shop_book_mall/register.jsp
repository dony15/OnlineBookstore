<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>bookStore注册页面</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/jquery-color.js"></script>
	<script type="text/javascript" src="js/pattern.js"></script>
	<script type="text/javascript" src="js/js-utils.js"></script>
<script type="text/javascript">
	<%--function changeImage() {--%>

		<%--document.getElementById("img").src = "${pageContext.request.contextPath}/imageCode?time="--%>
				<%--+ new Date().getTime();--%>
        <%--onCodekeyup();--%>
	<%--}--%>
    function changeImageClick() {
        changeImage("img","${pageContext.request.contextPath}/imageCode?time=")
    }

    /**
	 * 检验邮箱格式
     */
    var tempemail=false;
	function onemailkeyup() {
		var email = $("#email").val();
    	if (!ePattern.test(email)){
    	    $("#emailfont").html("邮箱格式有误");
    	    $("#emailfont").css("color","red");
			return false;
		}
		var url="checkuserinfo?type=email&userInfo="+email;
		$.get(url,function (data,status) {
		    if (status == "success") {
			if ("true"==data){
                $("#emailfont").html("邮箱可用");
                $("#emailfont").css("color","green");
                tempemail=true;

			}else{
                $("#emailfont").html("邮箱已占用");
                $("#emailfont").css("color","red");
                tempemail=false;
			}
            }
        });
		return true;
	}

    /**
     * 检验用户名格式
     */
    var tempeusername=false;
    function onusernamekeyup() {
        var username = $("#username").val();
        if (!uPattern.test(username)){
            $("#usernamefont").html("用户名格式有误");
            $("#usernamefont").css("color","red");
            return false;
        }
        var url="checkuserinfo?type=username&userInfo="+username;
        $.get(url,function (data,status) {
            if (status == "success") {
                if ("true"==data){
                    $("#usernamefont").html("用户名可用");
                    $("#usernamefont").css("color","green");
                    tempeusername=true;

                }else{
                    $("#usernamefont").html("用户名已占用");
                    $("#usernamefont").css("color","red");
                    tempeusername=false;
                }
            }
        });
        return true;
    }

    /**
     * 检验手机号格式
     */
    var temptelephone=false;
    function ontelephonekeyup() {
        var telephone = $("#telephone").val();
        if (!mPattern.test(telephone)){
            $("#telephonefont").html("手机号格式有误");
            $("#telephonefont").css("color","red");
            return false;
        }
        var url="checkuserinfo?type=telephone&userInfo="+telephone;
        $.get(url,function (data,status) {
            if (status == "success") {
                if ("true"==data){
                    $("#telephonefont").html("手机号可用");
                    $("#telephonefont").css("color","green");
                    temptelephone=true;

                }else{
                    $("#telephonefont").html("手机号已占用");
                    $("#telephonefont").css("color","red");
                    temptelephone=false;
                }
            }
        });
        return true;
    }

    /**
     * 检验密码格式
     */
    function onpasswordkeyup() {
        var password = $("#password").val();
        if (!pPattern.test(password)){
            $("#passwordfont").html("密码格式有误");
            $("#passwordfont").css("color","red");
            return false;
        }else{
            $("#passwordfont").html("密码格式正确");
            $("#passwordfont").css("color","green");
            return true;
        }

    }

    /**
     * 检验二次密码是否一致
     */
    function onrepasswordkeyup() {
        var repassword = $("#repassword").val();
        var password = $("#password").val();
        if (!(repassword==password)){
            $("#repasswordfont").html("两次密码输入不一致");
            $("#repasswordfont").css("color","red");
            return false;
        }else{
            $("#repasswordfont").html("输入正确");
            $("#repasswordfont").css("color","green");
            return true;
        }

    }

    /**
	 *验证码校验
	 *
	*/
    function onCodekey(){

        var url="getcodeword";
       return onCodekeyup("imageCode","Codefont",url);
	}

        /**
	 * 提交验证
     */
	function onsubmint() {
		var bool=onemailkeyup()&&onusernamekeyup()
			&&ontelephonekeyup()&&onpasswordkeyup()&&onrepasswordkeyup()&&onCodekey();
		bool=bool&&tempemail&&tempeusername&&temptelephone;
		return bool;
    }


</script>
</head>


<body class="main">
	<%@include file="head.jsp"%>
	<%--导入头 --%>
	<%@include file="menu_search.jsp"%><%--导入导航条与搜索 --%>

	<div id="divcontent">
		<form action="${pageContext.request.contextPath}/register"
			method="post">
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding:30px">
						<h1>新会员注册</h1>
						
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">会员邮箱：</td>
								<td style="width:40%">
								<input type="text" class="textinput"
									name="email" onkeyup="onemailkeyup()" id="email" /></td>
								<td><font color="#999999" id="emailfont">请输入有效的邮箱地址</font></td>
							</tr>
							<tr>
								<td style="text-align:right">会员名：</td>
								<td>
									<input type="text" class="textinput" name="username" onkeyup="onusernamekeyup()" id="username" />
								</td>
								<td><font color="#999999" id="usernamefont">用户名设置至少6位,由大写字母开头</font></td>
							</tr>
							<tr>
								<td style="text-align:right">密码：</td>
								<td><input type="password" class="textinput"
									name="password" onkeyup="onpasswordkeyup()" id="password" /></td>
								<td><font color="#999999" id="passwordfont">由数字+大小写字母+特殊字符@!#$^</font></td>
							</tr>
							<tr>
								<td style="text-align:right">重复密码：</td>
								<td><input type="password" class="textinput"
									name="repassword"  onkeyup="onrepasswordkeyup()" id="repassword" /></td>
								<td><font color="#999999" id="repasswordfont"></font></td>
							</tr>
							<tr>
								<td style="text-align:right">性别：</td>
								<td colspan="2">&nbsp;&nbsp;<input type="radio"
									name="gender" value="男" checked="checked" /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"
									name="gender" value="女" /> 女</td>
							</tr>
							<tr>
								<td style="text-align:right">联系电话：</td>
								<td colspan="1"><input type="text" class="textinput"
									 name="telephone" onkeyup="ontelephonekeyup()" id="telephone" /></td>
								<td><font color="#999999" id="telephonefont">用户名设置至少6位</font></td>
							</tr>
							<tr>
								<td style="text-align:right">个人介绍：</td>
								<td colspan="2"><textarea class="textarea" name="introduce"></textarea>
								</td>
							</tr>

						</table>



						<h1>注册校验</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align:right; width:20%">输入校验码：</td>
								<td style="width:50%"><input type="text" class="textinput" id="imageCode" name="imageCode"  onkeyup="onCodekey()" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align:right;width:20%;">&nbsp;</td>
								<td colspan="1" style="width:50%"><img
									src="${pageContext.request.contextPath}/imageCode" width="180"
									height="30" class="textinput" style="height:30px;" id="img" />&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick='changeImageClick()'>看不清换一张</a>
								</td>
                                <td>
                                    <font id="Codefont"></font>
                                </td>
							</tr>
						</table>



						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top:20px; text-align:center"><input
									type="image" src="images/signup.gif" name="submit" border="0" onclick="return onsubmint()">
								</td>
							</tr>
						</table></td>
				</tr>
			</table>
		</form>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width:10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left:175px" /></td>
				<td style="padding-top:5px; padding-left:50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b> </font> </a></td>
			</tr>
			<tr>
				<td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT
							2015 &copy; eShop All Rights RESERVED.</b> </font></td>
			</tr>
		</table>
	</div>


</body>
</html>
