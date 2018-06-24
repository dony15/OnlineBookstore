<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.dony15.shop.bean.ShoppingCar" %>
<%@ page import="com.dony15.shop.pojo.User" %>
<%@ page import="com.dony15.shop.pojo.Car" %>
<%@ page import="java.math.BigDecimal" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>电子书城</title>
    <link rel="stylesheet" href="css/main.css" type="text/css"/>


</head>


<body class="main">
<jsp:include page="head.jsp"/>

<jsp:include page="menu_search.jsp"/>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>

            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
                        href="cart.jsp">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单
                </div>


                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="0">
                                <tr>
                                    <td><img src="images/buy2.gif" width="635" height="38"/>
                                        <%
                                            User user = (User) session.getAttribute("user");
                                        %>
                                        <p>您好：<%
                                            out.print(user.getUsername());
                                            if ("男".equals(user.getGender())) {
                                                out.print("小哥哥！");
                                            }
                                            if ("女".equals(user.getGender())) {
                                                out.print("小姐姐！");
                                            }
                                        %>
                                            欢迎您来到商城结算中心</p></td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td width="10%">序号</td>
                                                <td width="40%">商品名称</td>
                                                <td width="10%">价格</td>
                                                <td width="10%">类别</td>
                                                <td width="10%">数量</td>
                                                <td width="10%">小计</td>

                                            </tr>
                                        </table>
                                        <form action="insertToOrder" method="post">
                                        <table width="100%" border="0" cellspacing="0">

                                            <%
                                                ShoppingCar shoppingCar = (ShoppingCar) request.getAttribute("shoppingCar");
                                                List<Car> cars = shoppingCar.getItems();
                                                BigDecimal sum = new BigDecimal(0);
                                                for (int i = 0; i < cars.size(); i++) {
                                                    Car car = cars.get(i);
                                                    //小计费用计算
                                                    BigDecimal sumOne = car.getPrice().multiply(new BigDecimal(car.getCount()));
                                                    out.print(" <tr><td width=\"10%\">" + (i + 1) + "</td>");
                                                    out.print(" <td width=\"40%\">" + car.getName() + "</td>");
                                                    out.print(" <td width=\"10%\">" + car.getPrice() + "</td>");
                                                    out.print(" <td width=\"10%\">" + car.getProductType() + "</td>");
                                                    out.print("<td width=\"10%\"><input name=\"text\" type=\"text\" value=\"" + car.getCount() + "\" style=\"width:20px\" readonly=\"readonly\" /></td>");
                                                    out.print("<td width=\"10%\">" + sumOne + "</td></tr>");
                                                    sum = sum.add(sumOne);
                                                }


                                            %>

                                            <%--<tr>--%>
                                            <%--<td width="10%">1</td>--%>
                                            <%--<td width="40%">Thinking In Java</td>--%>
                                            <%--<td width="10%">100</td>--%>
                                            <%--<td width="10%">计算机</td>--%>
                                            <%--<td width="10%"><input name="text" type="text"--%>
                                            <%--value="10" style="width:20px" readonly="readonly" /></td>--%>
                                            <%--<td width="10%">1000</td>--%>

                                            <%--</tr>--%>
                                        </table>


                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;"><font
                                                        style="color:#FF0000"
                                                       >合计：&nbsp;&nbsp;<%out.print(sum);%>元</font>
                                                </td>
                                            </tr>
                                        </table>

                                        <p>
                                            收货地址：<input name="receiverAddress" type="text" value=""
                                                        placeholder="请输入收货地址"
                                                        style="width:350px"/>&nbsp;&nbsp;&nbsp;&nbsp;<a
                                                href="#"></a>
                                            <br/> 收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input placeholder="请输入真实姓名"
                                                                                     name="receiverName" type="text"
                                                                                     value=""
                                                                                     style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp;<a
                                                href="#"></a>
                                            <br/> 联系方式：<input type="text" name="receiverPhone"
                                                              placeholder="请输入稳当的手机号~"
                                                              value="" style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp;

                                        </p>
                                        <hr/>
                                        <p style="text-align:right">
                                            <input type="image" src="images/gif53_029.gif" width="204" height="51" border="0">
                                        </p>
                                        </form>
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


<jsp:include page="foot.jsp"/>


</body>
</html>
