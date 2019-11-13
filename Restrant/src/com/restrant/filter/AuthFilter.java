package com.restrant.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		//获取根目录所对应的绝对路径
		String currentURL=request.getRequestURI();
		HttpSession session=request.getSession(false);
		//如果直接访问购物车shopCart页面则验证
		if(currentURL.indexOf("shopCart.jsp")>-1){
			//判断当前页是否是重定向以后的登录页面，如果是就不判断session
			if(session==null || session.getAttribute("user")==null){
				response.sendRedirect(request.getContextPath()+"/toShowMeal");
				return;
			}
		}
		//加入filter链继续向下执行
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
