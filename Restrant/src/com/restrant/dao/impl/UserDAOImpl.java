package com.restrant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.restrant.dao.UserDAO;
import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public class UserDAOImpl implements UserDAO {

	SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List search(Users condition) {
		List list=null;
		//通过sessionFactory获得Session
		Session session=sessionFactory.getCurrentSession();
		//创建Criteria对象
		Criteria c=session.createCriteria(Users.class);
		//使用Example工具类创建示例对象
		Example example=Example.create(condition);
		//为Criteria对象指定示例对象example作为查询条件		
		c.add(example);			
		list= c.list();	  //执行查询，获得结果		
		return list;
	}

	//添加用户
	@Override
	public void addUsers(Users users) {
		Session session=sessionFactory.getCurrentSession();
		session.save(users);
	}

	//管理员登录验证
	@Override
	public List search(Admin condition) {
		List list=null;
		//通过sessionFactory获得Session
		Session session=sessionFactory.getCurrentSession();
		//创建Criteria对象
		Criteria c=session.createCriteria(Admin.class);
		//使用Example工具类创建示例对象
		Example example=Example.create(condition);
		//为Criteria对象指定示例对象example作为查询条件		
		c.add(example);			
		list= c.list();	  //执行查询，获得结果		
		return list;
	}

	//修改个人信息
	@Override
	public void modifyUsers(Users users) {
		Session session=sessionFactory.getCurrentSession();
		session.update(users);
	}

}
