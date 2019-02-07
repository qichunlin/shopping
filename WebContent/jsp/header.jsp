<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Legend Shopping</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	
	<!-- 使用aJax -->
	<script type="text/javascript">
		$(function(){
			//触发ajax执行
			/* $.post(url,date,function(msg){}); */
			$.post("CategoryServlet",{method:"findAllCategory"},function(msg){
				//console.log(msg);//调试  字符串信息是否接收到了
				//msg是json的字符串，把字符串转换为json对象    通过对象去点里面的属性
				var jsonObj = JSON.parse(msg);  //集合对象
				//遍历集合对象    第一个是遍历的对象    function参数   i代表下标   c代表对象
				$.each(jsonObj,function(i,c){
					//获取ul对象
					var li = "<li><a href='ProductServlet?method=findProductByCidWithPage&num=1&cid="+c.cid+"'>"+c.cname+"</a></li>";
					$("#myUL").append(li);
				});
				
			});
		})
	</script>

</head>
<body>

		<!--描述：菜单栏 -->
		<div class="container-fluid">
			<div class="col-md-4">
				<h1 style="color: #28A4C9; font-weight: bold;">蓝&nbsp;&nbsp;桥</h1>
			</div>
			<div class="col-md-5"></div>
			<div class="col-md-3" align="right"
				style="padding-top: 20px; line-height: 35px;">
				<ol class="list-inline">

					<!-- <c:if test="el表达式"></c:if> -->
					<%-- <%="欢迎,"+session.getAttribute("username") %> --%>
					<%-- ${作用域对象 } --%>

					<c:if test="${empty users }">
						<!-- 未登陆 -->
						<li><a
							href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
						<li><a
							href="${pageContext.request.contextPath}/UserServlet?method=registerUI">注册</a></li>
					</c:if>

					<c:if test="${!empty users }">	
					欢迎,${users.username }
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginOut">退出</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
						<li><a
							href="${pageContext.request.contextPath}/OrderServlet?method=findOrderAllByUidWithPage&num=1">我的订单</a></li>
					</c:if>

				</ol>
			</div>
		</div>


		<!--
            	描述：导航条
            -->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">首页</a>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav" id="myUL">
							
						</ul>
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search">
							</div>
							<button type="submit" class="btn btn-default">Submit</button>
						</form>

					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
</body>
</html>