package com.restrant.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.UserBiz;
import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public class UserAction extends ActionSupport implements RequestAware,SessionAware {
	//用于封装登录页面登录用户类型（普通用户或管理员）
	private String type;	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	//定义用于保存用户登录表单参数的两个属性
	private String loginName;
	private String loginPwd;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	//封装注册表单参数
	private  Users user;	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	UserBiz userBiz;	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
    //登录验证	
	public String validateLogin() throws Exception {
		List list;
		if("userlogin".equals(type)){       //用户登录验证
			Users condition=new Users();
			condition.setLoginName(loginName);
			condition.setLoginPwd(loginPwd);	
			list=userBiz.login(condition);
			if(list.size()>0){
				//将用户对象存入Session
				session.put("user", list.get(0));	
			}
		}
        if("adminlogin".equals(type)){        //管理员登录验证
			Admin condition=new Admin();
			condition.setLoginName(loginName);
			condition.setLoginPwd(loginPwd);	
			list=userBiz.login(condition);
			if(list.size()>0){
				//将用户对象存入Session
				session.put("admin", list.get(0));	
			}
		}
		//转到名为toShowMeal的Action
		return "toShowMeal"; 
	}
	
	//用户注册
	public String register() throws Exception {
		userBiz.addUsers(user);
		return "show";
	}
	
	//修改个人信息
	public String modifyUsers() throws Exception {
		userBiz.modifyUsers(user);
		return "show";
	}
	
	//用户注销
	public String logOut() throws Exception {
		if("userlogout".equals(type)){
			session.remove("user");
		}
		if("adminlogout".equals(type)){
			session.remove("admin");
		}
		return "show";
	}

	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

}
