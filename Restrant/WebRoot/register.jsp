<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri='/struts-tags' prefix='s' %>
<html>
	<head>
		<title>用户注册页面</title>		
		<link rel="stylesheet" href="/Restrant/css/styles.css" type="text/css" />
		<script type="text/javascript">
			function usercheck(){
				if(document.ufrm.username.value.length==0){
					alert("请输入用户名");
					return false;
				}
				if(document.ufrm.userpwd.value.length==0){
					alert("请输入密码");
					return false;
				}
				return true;
			}
			function admincheck(){
				if(document.afrm.adminname.value.length==0){
					alert("请输入登录名");
					return false;
				}
				if(document.afrm.adminpwd.value.length==0){
					alert("请输入密码");
					return false;
				}
				return true;
			}
		</script>
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
					
					<div style="background-image:url(images/004.gif)">
						&nbsp;
					</div>				    
                    <form action="register" method="post" name="frm"
						onsubmit="return check()">
						<table width="100%" cellspacing="0" cellpadding="3" align="center"
							style="text-align:center; border:1px #cccccc solid;">
							<tr style="background-color:#CCCCFF;">
								<td colspan="2">
									填写注册信息
								</td>
							</tr>
							<tr>
								<td align="right" style="width:320px;" >
									登录名称：
								</td>
								<td align="left">
									<input type="text" name="user.loginName" style="width:220px;" />									
								</td>
							</tr>
							<tr>
								<td align="right" style="width:320px;">
									登录密码：
								</td>
								<td align="left">
									<input type="password" name="user.loginPwd" style="width:220px;" />									
								</td>
							</tr>							
							<tr>
								<td align="right" style="width:320px;">
									真实姓名：
								</td>
								<td align="left">
									<input type="text" name="user.trueName" style="width:220px;" />									
								</td>
							</tr>						
							<tr>
								<td align="right" style="width:320px;">
									电话号码：
								</td>
								<td align="left">
									<input type="text" name="user.phone" style="width:220px;" />									
								</td>
							</tr>
							<tr>
								<td align="right" style="width:320px;">
									电子邮件：
								</td>
								<td align="left">
									<input type="text" name="user.email" style="width:220px;" />									
								</td>
							</tr>
							<tr>
								<td align="right" style="width:320px;">
									默认地址：
								</td>
								<td align="left">
									<input type="text" name="user.address" style="width:220px;" />									
								</td>
							</tr>
							<tr style="background-color:#CCCCFF;">
								<td colspan="2">
									<input name="register" type="submit" id="register" value="注册" />
								</td>
							</tr>
						</table>
					</form>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br>
					<hr width=100%>
					<br>					
					<br>
					<br>
				</td>
			</tr>
		</table>
	</body>
</html>
