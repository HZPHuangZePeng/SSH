package com.restrant.biz.impl;

import java.util.List;
import com.restrant.biz.OrdersBiz;
import com.restrant.dao.OrdersDAO;
import com.restrant.entity.Orders;
import com.restrant.entity.Pager;

public class OrdersBizImpl implements OrdersBiz {

	OrdersDAO ordersDAO;
	
	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}
	
	//删除指定编号的订单
	@Override
	public void deleteOrdersByOid(int oid) {
		Orders orders=ordersDAO.getOrdersByOid(oid);	
		ordersDAO.deleteOrders(orders);
	}

	@Override
	public Orders getOrdersByOid(int oid) {
		return ordersDAO.getOrdersByOid(oid);
	}

	//获取指定用户的订单列表
	@Override
	public List getOrdersByUserId(int userId) {
		return ordersDAO.getOrdersByUserId(userId);
	}

	//处理订单
	@Override
	public void handleOrders(Orders orders) {
		ordersDAO.updateOrders(orders);
	}

	//获取指定页显示的订单列表
	@Override
	public List getAllOrders(int page) {
		return ordersDAO.getAllOrders(page);
	}
	
	//获取所有订单数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	@Override
	public Pager getPagerOfOrders() {
		int count= ordersDAO.getCountOfAllOrders();
		//使用分页类Pager定义对象
		Pager pager=new Pager();
		//设置pager对象中的perPageRows属性，表示每页显示的记录数
		pager.setPerPageRows(6);
		//设置pager对象中的rowCount属性，表示记录总数
		pager.setRowCount(count);
	    //返回pager对象
		return pager;
	}

	//获取满足条件、指定页显示的订单列表
	@Override
	public List getOrdersByCondition(Orders condition, int page) {
		return ordersDAO.getOrdersByCondition(condition, page);
	}

	//获取满足条件的订单数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	@Override
	public Pager getPagerOfOrders(Orders condition) {
		int count= ordersDAO.getCountOfOrders(condition);
		//使用分页类Pager定义对象
		Pager pager=new Pager();
		//设置pager对象中的perPageRows属性，表示每页显示的记录数
		pager.setPerPageRows(6);
		//设置pager对象中的rowCount属性，表示记录总数
		pager.setRowCount(count);
	    //返回pager对象
		return pager;
	}
	

}
