<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.dony15.shop.pojo.User" %>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td><a href="index.jsp"><img src="images/logo.png"
					width="95" height="30" border="0" /> </a></td>

		<%
			User user= (User) session.getAttribute("user");
			String userHref="";
			if (user==null){
			    userHref="login.jsp";
			}else if("user".equals(user.getRole())){
			    userHref="myAccount.jsp";
			}else if("admin".equals(user.getRole())){
				userHref="admin/login/home.jsp";
			}

		%>
			<td style="text-align:right"><img src="images/cart.gif"
				width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a href="goToCar">购物车</a>
				| <a href="#">帮助中心</a>
				| <a href="<%out.print(userHref);%>">我的帐户</a>
				| <a href="register.jsp">新用户注册</a></td>
		</tr>
	</table>
</div>