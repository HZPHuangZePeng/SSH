package com.restrant.dao;

import java.util.List;

import com.restrant.entity.Meal;
import com.restrant.entity.Orders;

public interface OrdersDAO {
		
	//根据订单编号加载订单对象
    public Orders getOrdersByOid(int oid);
    
    //获取指定用户的订单列表
    public List getOrdersByUserId(int userId);
    
    //获取指定页显示的订单列表
    public List getAllOrders(int page);
    
    //统计所有订单总数
  	public Integer getCountOfAllOrders();
    
    //获取符合条件、指定页显示的订单列表
  	public List getOrdersByCondition(Orders condition,int page);
  	
  	//统计符合条件的订单总数
  	public Integer getCountOfOrders(Orders condition);   
    
    
	//删除订单对象
	public void deleteOrders(Orders orders);
	
	//更新订单对象
	public void updateOrders(Orders orders);		
	
}
