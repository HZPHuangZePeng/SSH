package com.restrant.biz.impl;

import java.util.List;

import com.restrant.biz.OrderDtsBiz;
import com.restrant.dao.OrderDtsDAO;
import com.restrant.entity.Orderdts;

public class OrderDtsBizImpl implements OrderDtsBiz {

	OrderDtsDAO orderDtsDAO;	
	public void setOrderDtsDAO(OrderDtsDAO orderDtsDAO) {
		this.orderDtsDAO = orderDtsDAO;
	}
	
	//生成订单子表（订单明细）
	@Override
	public void addOrderDts(Orderdts dts) {
		orderDtsDAO.addOrderDts(dts);		
	}
	
	//根据订单主表编号获取订单明细列表
	@Override
	public List getOrderDtsByOid(int oid) {		
		return orderDtsDAO.getOrderDtsByOid(oid);
	}

}
