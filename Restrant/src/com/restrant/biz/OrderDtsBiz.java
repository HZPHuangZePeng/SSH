package com.restrant.biz;

import java.util.List;

import com.restrant.entity.Orderdts;

public interface OrderDtsBiz {
	
	//生成订单子表（订单明细）
    public void addOrderDts(Orderdts dts);
    
    //根据订单主表编号获取订单明细列表
  	public List getOrderDtsByOid(int oid);	

}
