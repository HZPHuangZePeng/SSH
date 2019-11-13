<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri='/struts-tags' prefix='s' %>

<html>
	<head>
		<title>订单处理</title>       
		<link rel="stylesheet" href="/Restrant/css/styles.css" type="text/css" />
	</head>

	<body>
		<table width="90%" height="629" border="0" cellpadding="0"
			cellspacing="0" align="center">
			<tr>
				<td width="200" height="101">
					<img src="images/jb_logo.jpg" width="64" height="32" />
					<strong><span
						style="font-size: 20px;">网上订餐系统</span> </strong>
				</td>
				<td width="640" style="padding-left: 40px;">
					
				</td>
			</tr>
			<tr>
				<td height="41" colspan="2"
					style="background-image:url(images/001.gif);" align="center">
					|
					<a href="/Restrant/toShowMeal">网站首页</a> |
					<s:if test="(#session.admin==null) && (#session.user==null)">					
					<a href="register.jsp">用户注册</a> |
					<a href="login.jsp?role=user">用户登录</a> |
					<a href="login.jsp?role=admin">管理员登录</a> |
					</s:if>
					<s:if test="#session.user!=null">
					<a href="modifyMyInfo.jsp">修改个人信息</a> |
					<a href="shopCart.jsp">我的购物车</a> |
					<a href="/Restrant/toMyOrders">我的订单</a> |
					<a href="/Restrant/logOut?type=userlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
					<font style="color: red">欢迎您：${sessionScope.user.trueName }</font>					
					</s:if>
					<s:if test="#session.admin!=null">
					<a href="/Restrant/toAddMeal">添加餐品</a> |
					<a href="/Restrant/toManageMeal">管理餐品</a> |
					<a href="/Restrant/toManageOrders">订单处理</a> |
					<a href="/Restrant/logOut?type=adminlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
					<font style="color: red">欢迎您：${sessionScope.admin.loginName }</font>
					</s:if>
					
				</td>
			</tr>
			<tr>
				<td valign="top" align="center">
					<p>
						<img src="images/left_top.jpg" width="215" height="100" />
						<br>
						<img src="images/003.gif" width="191" height="8">
				</td>
				<td valign="top" width="80%">
					<img src="images/001.jpg" width="595" height="72" />
					<br />
					<div align="left">
				      <s:form theme="simple" method="post" action="toManageOrders">
		                <s:label value="订单号：" />
		        	    <s:textfield name="orders.oid" />
						<s:label value="订单状态：" />
		        	    <s:select list="#{'全部':'全部','未处理':'未处理','已处理':'已处理'}" name="orders.orderState" listKey="key" listValue="value" />
		        	    <s:submit value="查询" />						
					  </s:form>
					</div>
					
					<div style="background-image:url(images/004.gif)">
						&nbsp;
					</div>					
					<br />
					<img src="images/icon_order.gif" align="top" />
					订单列表
					<br />
					<hr />
					<table align="center" width="95%" cellspacing="0" cellpadding="3"
						style="text-align:center; border:1px #cccccc solid;">
						<tr style="background-color:#CCCCFF;">
							<td>订单编号</td>
							<td>订单时间</td>
							<td>订单状态</td>
							<td>总额</td>
							<td>处理</td>
						</tr>				
						<s:set var="total" value="0" />								
						<s:iterator id="orders" value="#request.ordersList">
						<tr style="background-color:#FFFFFF;">						  
							<td>								
								<s:property value="oid"/>
							</td>
							<td>								
								<s:date name="orderTime" format="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<s:property value="orderState"/>
							</td>
							<td>
								<s:property value="orderPrice"/>
							</td>							
							<td>
							    <s:if test="#orders.orderState=='未处理'">
								  <a href="/Restrant/handleOrders?oid=${oid}"><img src="images/handle.gif"
								width="12" height="12" /></a>
								</s:if>
							</td>
						</tr>										    			
						</s:iterator>
						
						<!-- 分页超链接开始 -->
						<table align="right">
	  <tr>
		<td width="130"></td>
		<td width="80">
			<s:if test="pager.curPage>1">
				<A href='/Restrant/toManageOrders?pager.curPage=1&orders.oid=${requestScope.oid}&orders.orderState=${requestScope.orderState}'>首页</A>&nbsp;&nbsp;
				<A href='/Restrant/toManageOrders?pager.curPage=${pager.curPage-1 }&orders.oid=${requestScope.oid}&orders.orderState=${requestScope.orderState}'>上一页</A>
			</s:if>
		</td>
		<td width="80">
			<s:if test="pager.curPage < pager.pageCount">
				<A href='/Restrant/toManageOrders?pager.curPage=${pager.curPage+1}&orders.oid=${requestScope.oid}&orders.orderState=${requestScope.orderState}'>下一页</A>&nbsp;&nbsp;
				<A href='/Restrant/toManageOrders?pager.curPage=${pager.pageCount }&orders.oid=${requestScope.oid}&orders.orderState=${requestScope.orderState}'>尾页</A>
			</s:if>
		</td>
		<td>共${pager.rowCount}记录，共${pager.curPage}/${pager.pageCount}页&nbsp;&nbsp;
		
		</td>
	  </tr>
	</table>
						
						<!-- 分页超链接结束-->
						
					</table>
					<br />
					
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br>
					<hr width=100%>
					<br>	
					<br>
				</td>
			</tr>
		</table>
	</body>
</html>
