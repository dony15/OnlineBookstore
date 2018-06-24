<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.dony15.shop.bean.ShoppingCar" %>
<%@ page import="com.dony15.shop.pojo.Car" %>
<%@ page import="java.math.BigDecimal" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	//手动输入框输入数量,自动调整 库存/小计/总价
	//问题1:输入后无法正确响应库存数量X
	//问题2:超出限定条件无法重置(刷新会重复提交,考虑使用ajax局部刷新重置)X
	//问题3:...自动重置输入数据和小计库存,合算增加异常X
    // onkeyup=\"onCountup("+(i+1)+","+car.getCount()+")\"
    // function onCountup(m,count) {
     //    var text = document.getElementById("text"+m);
     //    var carsumOne = document.getElementById("carsumOne"+m);
     //    var carPrice = document.getElementById("carPrice"+m);
     //    var carsum = document.getElementById("carsum");
    //
     //    var textNo=count;
     //    // var PnumNo=new Number(carPnum.innerHTML)-count;
     //    var sumOneNo=new Number(carPrice.innerHTML)*count;
     //    var carsumNo=new Number(carsum.innerHTML)+new Number(carPrice.innerHTML)*count;
     //    if (textNo<0||sumOneNo<0){
     //        //库存和产品简单约束,需要更进一步对比真实库存,避免注入式攻击
	// 		alert("非法输入,请重新进入购物车");
	// 		window.location.href="cart.jsp";
     //        return;
     //    }else{
     //        text.value=textNo;
     //        // carPnum.innerHTML=PnumNo;
     //        carsumOne.innerHTML=sumOneNo;
     //        carsum.innerHTML=carsumNo;
     //    }
    // }

	function onCarChange(m,n){
		var cardown = document.getElementById("cardown"+m);
		var carup = document.getElementById("carup"+m);
		var text = document.getElementById("text"+m);
		var carsumOne = document.getElementById("carsumOne"+m);
		var carPnum = document.getElementById("carPnum"+m);
		var carPrice = document.getElementById("carPrice"+m);
		var carsum = document.getElementById("carsum");

		var textNo=new Number(text.value)+n;
		var PnumNo=new Number(carPnum.innerHTML)-n;
		var sumOneNo=new Number(carsumOne.innerHTML)+new Number(carPrice.innerHTML)*n;
		var carsumNo=new Number(carsum.innerHTML)+new Number(carPrice.innerHTML)*n;
		if (textNo<0||PnumNo<0||sumOneNo<0){
            //库存和产品简单约束,需要更进一步对比真实库存,避免注入式攻击
		    return;
		}else{
		    text.value=textNo;
            carPnum.innerHTML=PnumNo;
            carsumOne.innerHTML=sumOneNo;
            carsum.innerHTML=carsumNo;
		}
    }

    function onremovecar(m) {
		var trid = document.getElementById("tr"+m);
        var carsum = document.getElementById("carsum");
        var carsumOne = document.getElementById("carsumOne"+m);
		carsum.innerHTML=new Number(carsum.innerHTML)-new Number(carsumOne.innerHTML);
    	trid.parentNode.removeChild(trid);
		return false;

	}

	function onsublist() {
		var formid = document.getElementById("formid");
		formid.action="editandgolist";
        formid.submit();
        return false;
    }

    function onsuborder() {
        var formid = document.getElementById("formid");
        formid.action="editandgoorder";
        formid.submit();
        return false;
    }


</script>

</head>

<body class="main">

	<jsp:include page="head.jsp" />

	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
					</div>

					<table cellspacing="0" class="infocontent">
						<tr>
							<td><img src="ad/page_ad.jpg" width="666" height="89" />
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td><img src="images/buy1.gif" width="635" height="38" />
										</td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="30%">商品名称</td>
													<td width="10%">价格</td>
													<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
													<td width="10%">库存</td>
													<td width="10%">小计</td>
													<td width="10%">取消</td>
												</tr>
											</table>
											<form id="formid" action="#" method="post">
												<table width="100%"  border="0" cellspacing="0">

													<%
														ShoppingCar shoppingCar = (ShoppingCar) request.getAttribute("shoppingCar");
														List<Car> cars=shoppingCar.getItems();
														BigDecimal sum=new BigDecimal(0);
														for (int i = 0; i < cars.size(); i++) {
															Car car=cars.get(i);
															//小计费用计算
															BigDecimal sumOne=car.getPrice().multiply(new BigDecimal(car.getCount()));
															out.print(" <tr id=\"tr"+(i+1)+"\"><td width=\"10%\" name=\"carid\">"+(i+1)+"</td><td width=\"30%\" name=\"name\">"+car.getName()+"</td>");
															out.print("<input type='hidden' name='pid' value='"+car.getPid()+"' />");
															out.print(" <td width=\"10%\" id='carPrice"+(i+1)+"' name=\"price\" >"+car.getPrice()+"</td><td width=\"20%\">");
															out.print(" <input type=\"button\" id='cardown"+(i+1)+"' value='-' onclick='onCarChange("+(i+1)+",-1)' style=\"width:20px\">");
															out.print(" <input name=\"count\" type=\"text\" id='text"+(i+1)+"'   value='"+car.getCount()+"' style=\"width:40px;text-align:center\" /> <input type=\"button\" value='+' id='carup"+(i+1)+"' onclick='onCarChange("+(i+1)+",1)' style=\"width:20px\"></td>");
															out.print(" <td width=\"10%\" name=\"pnum\" id='carPnum"+(i+1)+"'>"+car.getPnum()+"</td><td width=\"10%\" name=\"sumOne\" id='carsumOne"+(i+1)+"'>"+sumOne+"</td>");
															out.print("<td width=\"10%\"><a href=\"#\" onclick=\"return onremovecar("+(i+1)+")\" style=\"color:#FF0000; font-weight:bold\">X</a></td></tr>");
															sum=sum.add(sumOne);
														}


													%>



													<%--<tr>--%>
														<%--<td width="10%">1</td>--%>
														<%--<td width="30%">Thinking in Java</td>--%>

														<%--<td width="10%">100</td>--%>
														<%--<td width="20%">--%>
														<%--<input type="button" value='-'--%>
															<%--style="width:20px">--%>

															<%--<input name="text" type="text"  value=10--%>
															<%--style="width:40px;text-align:center" /> <input--%>
															<%--type="button" value='+' style="width:20px">--%>

														<%--</td>--%>
														<%--<td width="10%">10</td>--%>
														<%--<td width="10%">1000</td>--%>

														<%--<td width="10%"><a href="#"--%>
															<%--style="color:#FF0000; font-weight:bold">X</a></td>--%>
													<%--</tr>--%>
												</table>
												


											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
															style="color:#FF6600; font-weight:bold" >合计：&nbsp;&nbsp;<font id="carsum" name="carsum"><%out.print(sum);%></font>元</font>
													</td>
												</tr>
											</table>
											</form>
											<div style="text-align:right; margin-top:10px">
												<a
													href="#" onclick="return onsublist()"><img
													src="images/gwc_jx.gif" border="0" /> </a>

												&nbsp;&nbsp;&nbsp;&nbsp;<a
													href="#" onclick="return onsuborder()"><img
													src="images/gwc_buy.gif" border="0" /> </a>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
