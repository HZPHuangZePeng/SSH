package com.restrant.interceptor;


import java.util.Map;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求的Action名
        String name = invocation.getInvocationContext().getName();
        if (name.equals("validateLogin")) {
            // 如果用户想登录，则使之通过
            return invocation.invoke();
        } else {
            // 取得Session
            ActionContext ac = invocation.getInvocationContext();
            Map session = (Map)ac.get(ServletActionContext.SESSION);
            if (session == null) {
                // 如果Session为空，则让用户登陆
                return "login";
            } else {
            	Users user = (Users)session.get("user");
                if (user == null) {
                	Admin admin=(Admin)session.get("admin");
                	if(admin==null){
                		// Session不为空，但Session中没有用户信息， 则让用户登陆
                		return "login";
                	}else{
                		// 管理登陆，放行
                        return invocation.invoke();
                	}                	
                    
                } else {
                    // 用户登陆，放行
                    return invocation.invoke();
                }
            }
        }

	}

}
