<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.dony15.shop.pojo.Product" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
	<script type="text/javascript" src="js/js-utils.js"></script>
	<script type="text/javascript">
		var pageNo=0;
		function gopage(n) {
            return productlimit(pageNo+n);
        }

		//limit
		function productlimit(pageno) {
            var thisURL=document.URL;
            var values=thisURL.split('?')[1];
            var category=values.split("&")[0].split("=")[1];

            var xhr = getXMLHttpRequest();
		    xhr.onreadystatechange=function () {
				if (xhr.readyState==4&&xhr.status==200){
				    var jsonStr = xhr.responseText;
				    var jsonObj=JSON.parse(jsonStr);
				    pageNo=jsonObj.pageNo;
				   var pageSize=jsonObj.pageSize;
				   var pageCount=jsonObj.pageCount;
				   var items=jsonObj.items;
				   console.log("对象:"+jsonObj);
				   console.log("字符串:"+jsonStr);
				   var html1="";
                    for (var i = 0; i <items.length; i++) {
						if (i%4==0){
						    html1=html1+"<tr>";
						}
						html1=html1+"<td><div class=\"divbookpic\">";
						html1=html1+"<p><a href=\"goProductInfo?pid="+items[i].id+"\"><img src=\""+items[i].img_url+"\" width=\"115\"height=\"129\" border=\"0\" /> </a></p></div>";
                        html1=html1+"<div class=\"divlisttitle\"><a href=\"goProductInfo?pid="+items[i].id+"\">书名:"+items[i].name+"<br />售价:"+items[i].price+" </a></div></td>";

                        if (i%4==3){
						    html1=html1+"</tr>";
						}
                    }
                    document.getElementById("producttable").innerHTML=html1;


                    /**
                     分页按钮
                    */
                    var html2="";
                    //上一页
                    if (pageNo==1){
                        html2=html2+"<li class=\"disablepage\">上一页 &lt;&lt;</li>";
                    }else{
                        html2=html2+"<li class=\"nextpage\"><a href=\"#\" onclick='return gopage(-1)'>上一页&lt;&lt;</a>";
                    }
                    //当前页
                    for (var i=0;i<pageCount;i++){
                        if ((i+1)==pageNo){
                            html2=html2+"<li class=\"currentpage\">"+(i+1)+"</li>";
                        }else{
                            html2=html2+"<li><a href=\"#\" onclick='return productlimit("+(i+1)+")'>"+(i+1)+"</a>";
                        }
                    }

                    //下一页
                    if (pageNo==pageCount){
                        html2=html2+"<li class=\"disablepage\">下一页 &gt;&gt;</li>";
                    }else{
                        html2=html2+"<li class=\"nextpage\"><a href=\"#\" onclick='return gopage(1)'>下一页&gt;&gt;</a>";
                    }

                     document.getElementById("pagelimit").innerHTML=html2;

                }
            }
            var url="";
		    url=url+"showProductPage2?pageNo="+pageno+"&pageSize=8&category="+category;
            xhr.open("get",url);
		    xhr.send();

		    return false;
        }




	</script>
</head>

<body class="main" onload="productlimit(1)">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<%
						List<Product> products = (List<Product>) request.getAttribute("products");
					%>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><% if ((request.getAttribute("products"))!=null){ out.print(products.get(0).getProductType());}else{out.print("暂无");}%></a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1><%if ((request.getAttribute("products"))!=null){out.print(products.get(0).getProductType());}else{out.print("暂无");}%></h1>&nbsp;&nbsp;&nbsp;&nbsp;共<%if ((request.getAttribute("products"))!=null){ out.print(products.size());}else{out.print("0");}%>件商品

								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>
								<table cellspacing="0" class="booklist" id="producttable">
									<%--<tr>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/101.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/102.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/103.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/104.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>


									<%--</tr>--%>
								</table>

								<%--<table cellspacing="0" class="booklist">--%>
									<%--<tr>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/101.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/102.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/103.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>
										<%--<td>--%>

											<%--<div class="divbookpic">--%>
												<%--<p>--%>
													<%--<a href="product_info.jsp"><img src="bookcover/104.jpg" width="115"--%>
														<%--height="129" border="0" /> </a>--%>
												<%--</p>--%>
											<%--</div>--%>

											<%--<div class="divlisttitle">--%>
												<%--<a href="product_info.jsp">书名:xxx<br />售价:xxx </a>--%>
											<%--</div></td>--%>


									<%--</tr>--%>
								<%--</table>--%>



								<div class="pagination" >
									<ul id="pagelimit">


										<%--<li class="disablepage">上一页 &lt;&lt;</li>--%>
										<%--<li class="currentpage">1</li>--%>
										<%--<li><a href="product_info.jsp">2</a>--%>
										<%--</li>--%>
										<%--<li><a href="product_info.jsp">3</a>--%>
										<%--</li>--%>
										<%--<li><a href="product_info.jsp">4</a>--%>
										<%--</li>--%>


										<%--<li class="nextpage"><a href="product_info.jsp">下一页&gt;&gt;</a>--%>
										<%--</li>--%>

									</ul>
								</div>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
