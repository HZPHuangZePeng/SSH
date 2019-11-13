package com.restrant.biz;

import java.util.List;

import com.restrant.entity.Meal;
import com.restrant.entity.Orderdts;
import com.restrant.entity.Orders;
import com.restrant.entity.Pager;

public interface OrdersBiz {
		
	//根据订单编号加载订单对象
    public Orders getOrdersByOid(int oid);
    
    //获取指定用户的订单列表
    public List getOrdersByUserId(int userId);
    
    //获取指定页显示的订单列表
    public List getAllOrders(int page);
    
    //获取所有订单数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
    public Pager getPagerOfOrders();	
    
    //获取满足条件、指定页显示的订单列表
    public List getOrdersByCondition(Orders condition,int page);
	
    //获取满足条件的订单数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	public Pager getPagerOfOrders(Orders condition);
	
	//删除指定编号的订单
	public void deleteOrdersByOid(int oid);
	
	//处理订单
	public void handleOrders(Orders orders);	
	
}
