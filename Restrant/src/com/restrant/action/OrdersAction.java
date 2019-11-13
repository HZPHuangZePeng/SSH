package com.restrant.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.OrderDtsBiz;
import com.restrant.biz.OrdersBiz;
import com.restrant.entity.CartItemBean;
import com.restrant.entity.Orderdts;
import com.restrant.entity.Orders;
import com.restrant.entity.Pager;
import com.restrant.entity.Users;

public class OrdersAction extends ActionSupport implements RequestAware,SessionAware {
	OrdersBiz ordersBiz;	
	public void setOrdersBiz(OrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
	}
	
	OrderDtsBiz orderDtsBiz;
	public void setOrderDtsBiz(OrderDtsBiz orderDtsBiz) {
		this.orderDtsBiz = orderDtsBiz;
	}
	
	//封装“查看”超链接传递来的参数oid的值
	int oid;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	//封装manageorders.jsp页面中根据订单号和订单状态查询时传递来的参数值
	private Orders orders;
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	//处理生成新订单请求
	public String addOrders() throws Exception {
		Orders orders=new Orders();
		orders.setOrderState("未处理");
		orders.setOrderTime(new Date());
		Users user=(Users)session.get("user");
	    orders.setUsers(user);
	    orders.setOrderPrice((Double)session.get("sumPrice"));
	    Map cart=(HashMap)session.get("cart");	    
	    Iterator iter = cart.keySet().iterator();
	    while (iter.hasNext()) {
	    	Object key = iter.next();
	    	CartItemBean cartItem = (CartItemBean)cart.get(key);
	    	Orderdts orderDts=new Orderdts();
	    	orderDts.setMeal(cartItem.getMeal());
	    	orderDts.setMealCount(cartItem.getQuantity());
	    	orderDts.setMealPrice(cartItem.getMeal().getMealPrice());
	    	orderDts.setOrders(orders);
	    	orderDtsBiz.addOrderDts(orderDts);
	    }
	    session.remove("cart");
		return "show";
	}
	
	//分页实体类
	private Pager pager;
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	//获取指定用户的订单列表,再转到我的订单页myorders.jsp
	public String toMyOrders() throws Exception {
		Users user=(Users)session.get("user");
		List myOrdersList=ordersBiz.getOrdersByUserId(user.getId());
		request.put("myOrdersList", myOrdersList);
		return "myorders";
	}
	
	//根据订单主表编号获取订单明细列表,再转到我的订单明细页myordersdetails.jsp
	public String toOrdersDetails() throws Exception {
		List ordersDtsList= orderDtsBiz.getOrderDtsByOid(oid);
		request.put("ordersDtsList", ordersDtsList);
		return "toOrdersDetails";
	}
	
	//删除指定编号的订单,再转到toMyOrders
	public String deleteOrders() throws Exception {		
		//调用业务方法从数据表中删除订单及明细
		ordersBiz.deleteOrdersByOid(oid);
		return "toMyOrders";
	}
	
	//获取所有订单列表,再转到订单处理页manageorders.jsp
	public String toManageOrders() throws Exception {
		int curPage=1;
		if(pager!=null)
			curPage=pager.getCurPage();
		List ordersList=null;		
		if(orders!=null){
			//指定查询条件,则获取满足条件、指定页的订单列表
			ordersList=ordersBiz.getOrdersByCondition(orders, curPage);
			pager=ordersBiz.getPagerOfOrders(orders);
			//将查询条件存入request范围，将作为分页超链接中的参数值
			if(orders.getOid()!=null)
				request.put("oid", orders.getOid());
		    if((orders.getOrderState()!=null) && !orders.getOrderState().equals(""))
				request.put("orderState", orders.getOrderState());
		}else{
			//没有指定查询条件，获取指定页的订单列表
			ordersList=ordersBiz.getAllOrders(curPage);
			//获取所有菜品数量,用来初始化分页类Pager对象
			pager=ordersBiz.getPagerOfOrders();
		}		
		//设置Pager对象中的待显示页页码
		pager.setCurPage(curPage);
		//将订单列表存入request范围
		request.put("ordersList", ordersList);
		return "manageorders";
	}
	
	//处理订单,再转到toManageOrders
	public String handleOrders() throws Exception {		
		//调用业务方法从数据表中删除订单及明细
		Orders orders=ordersBiz.getOrdersByOid(oid);
		orders.setOrderState("已处理");
		ordersBiz.handleOrders(orders);
		return "toManageOrders";
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
