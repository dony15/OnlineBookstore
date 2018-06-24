<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.dony15.shop.pojo.Order" %>
<%@ page import="java.math.BigDecimal" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>


<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
					</div>



					<table cellspacing="0" class="infocontent">
						<tr>
							<td>

								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<% Order order = (Order) request.getAttribute("orderInfo"); %>
											<p>订单编号:<%out.print(order.getId());%></p></td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="40%">商品名称</td>
													<td width="10%">价格</td>
													<td width="10%">数量</td>
													<td width="10%">小计</td>

												</tr>
											</table>
											<%


//												BigDecimal sum = new BigDecimal("0");
												for (int i = 0; i < order.getItems().size(); i++) {
													BigDecimal sumOne =order.getItems().get(i).getPrice().multiply(new BigDecimal(order.getItems().get(i).getCount()));
													out.print(" <table width=\"100%\" border=\"0\" cellspacing=\"0\">");
													out.print(" <tr><td width=\"10%\">"+(i+1)+"</td><td width=\"40%\">"+order.getItems().get(i).getName()+"</td>");
													out.print(" <td width=\"10%\">"+order.getItems().get(i).getPrice()+"</td><td width=\"10%\">"+order.getItems().get(i).getCount()+"</td>");
													out.print(" <td width=\"10%\">"+sumOne+"</td></tr></table>");

												}



												out.print(" <table cellspacing=\"1\" class=\"carttable\"><tr>");
												out.print(" <td style=\"text-align:right; padding-right:40px;\"><font");
												out.print("style=\"color:#FF0000\">合计：&nbsp;&nbsp;"+order.getPrices()+"</font></td></tr></table>");
												out.print("<p>收货地址："+order.getReceiverAddress()+"<br />");
												out.print("收货人: "+order.getReceiverName()+"<br />");
												out.print(" 联系方式: "+order.getReceiverPhone()+"</p>");


											%>
											<%--<table width="100%" border="0" cellspacing="0">--%>
												<%--<tr>--%>
													<%--<td width="10%">1</td>--%>
													<%--<td width="40%">Thinking In Java</td>--%>
													<%--<td width="10%">100</td>--%>
													<%--<td width="10%">10</td>--%>
													<%--<td width="10%">1000</td>--%>

												<%--</tr>--%>
											<%--</table>--%>
											<%--<table width="100%" border="0" cellspacing="0">--%>
												<%--<tr>--%>
													<%--<td width="10%">2</td>--%>
													<%--<td width="40%">Thinking In Java</td>--%>
													<%--<td width="10%">100</td>--%>
													<%--<td width="10%">10</td>--%>
													<%--<td width="10%">1000</td>--%>

												<%--</tr>--%>
											<%--</table>--%>

											<%--<table cellspacing="1" class="carttable">--%>
												<%--<tr>--%>
													<%--<td style="text-align:right; padding-right:40px;"><font--%>
														<%--style="color:#FF0000">合计：&nbsp;&nbsp;1000</font></td>--%>
												<%--</tr>--%>
											<%--</table>--%>

											<%--<p>--%>
												<%--收货地址：xxxx&nbsp;&nbsp;&nbsp;&nbsp;<br />--%>
												<%--收货人：&nbsp;&nbsp;&nbsp;&nbsp;tom&nbsp;&nbsp;&nbsp;&nbsp;<br />--%>
												<%--联系方式：13888888888&nbsp;&nbsp;&nbsp;&nbsp;--%>

											<%--</p>--%>
											<hr>
											<p style="text-align:right">
												<%
													System.out.println(order.getPaystate()+"这里测试总价-------------------------------");
												 if (order.getPaystate()==0){
												     out.print("<a href=\"pay?orderId="+order.getId()+"\">");
												     out.print("<img src=\"images/gif53_029.gif\" width=\"204\" height=\"51\" border=\"0\" /></a>");
												 }
												%>
												<%--<a href="pay?orderId=<%out.print(order.getId());%>"><img src="images/gif53_029.gif" width="204"--%>
													<%--height="51" border="0" /> </a>--%>
											</p>
										</td>
									</tr>
								</table>
							</td>


						</tr>


					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
