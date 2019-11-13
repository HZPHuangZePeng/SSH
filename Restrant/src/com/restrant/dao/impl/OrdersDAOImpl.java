package com.restrant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import com.restrant.dao.OrdersDAO;
import com.restrant.entity.Orders;

public class OrdersDAOImpl implements OrdersDAO {

	SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	

	//删除订单对象
	@Override
	public void deleteOrders(Orders orders) {
		Session session=sessionFactory.getCurrentSession();		
		session.delete(orders);
	}

	//根据订单编号加载订单对象
	@Override
	public Orders getOrdersByOid(int oid) {
		Session session=sessionFactory.getCurrentSession();
		return (Orders)session.get(Orders.class, oid);
	}

	//获取指定用户的订单列表
	@Override
	public List getOrdersByUserId(int userId) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		c.add(Restrictions.eq("users.id", userId));		
		return c.list();
	}

	//更新订单对象
	@Override
	public void updateOrders(Orders orders) {
		Session session=sessionFactory.getCurrentSession();
		session.update(orders);
	}

	//获取所有订单
	@Override
	public List getAllOrders(int page) {		
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}
	
	//统计所有订单总数
	@Override
	public Integer getCountOfAllOrders() {
		Integer count=null;
		try{
		Session session=sessionFactory.getCurrentSession();
		String hql="select count(o) from Orders o";
		Query query=session.createQuery(hql);
		count= Integer.parseInt(query.uniqueResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	
	//获取满足条件、指定页显示的订单列表
	@Override
	public List getOrdersByCondition(Orders condition, int page) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		if(condition!=null){
			if((condition.getOid()!=null) && (condition.getOid()>0)){
				//按订单号进行筛选
				c.add(Restrictions.eq("oid", condition.getOid()));
			}
			if((condition.getOrderState()!=null) && !condition.getOrderState().equals("") && !condition.getOrderState().equals("全部")){
				//按订单状态进行筛选
				c.add(Restrictions.eq("orderState", condition.getOrderState()));
			}
		}
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}
	
	//统计满足条件的订单总数
	@Override
	public Integer getCountOfOrders(Orders condition) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Orders.class);
		if(condition!=null){
			if((condition.getOid()!=null) && (condition.getOid()>0)){
				//按订单号进行筛选
				c.add(Restrictions.eq("oid", condition.getOid()));
			}
			if((condition.getOrderState()!=null) && !condition.getOrderState().equals("") && !condition.getOrderState().equals("全部")){
				//按订单状态进行筛选
				c.add(Restrictions.eq("orderState", condition.getOrderState()));
			}
		}
		return c.list().size();
	}	
	
}
