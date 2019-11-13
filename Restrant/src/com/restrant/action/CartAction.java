package com.restrant.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.MealBiz;
import com.restrant.biz.MealSeriesBiz;
import com.restrant.entity.CartItemBean;
import com.restrant.entity.Meal;

public class CartAction extends ActionSupport implements SessionAware {	
	//封装表单传递来的餐品编号mealId参数值
	private Integer mealId;
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}	
	public Integer getMealId() {
		return mealId;
	}
	//封装表单传递来的餐品数量quantity参数值
	int quantity;	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	MealBiz mealBiz;
	public void setMealBiz(MealBiz mealBiz) {
		this.mealBiz = mealBiz;
	}
	MealSeriesBiz mealSeriesBiz;	
	public void setMealSeriesBiz(MealSeriesBiz mealSeriesBiz) {
		this.mealSeriesBiz = mealSeriesBiz;
	}
	Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	//将餐品添加到购物车
	public String addtoshopcart() throws Exception {
		//从session中取出购物车，放入Map对象cart中
		Map cart=(Map)session.get("cart");
		//获取当前要添加到购物车的菜品
		Meal meal=mealBiz.getMealByMealId(mealId);
		//如果购物车不存在，则创建购物车(实例化HashMap类)，并存入session中
		if(cart==null){
			cart=new HashMap();
			session.put("cart", cart);
		}
		//如果存在购物车，则判断餐品是否在购物车中
		CartItemBean cartItem=(CartItemBean)cart.get(meal.getMealId());
		if(cartItem!=null){
		    //如果餐品在购物车中，更新其数量
			cartItem.setQuantity(cartItem.getQuantity()+1);
		}else{
			//否则，创建一个条目到Map中
			cart.put(meal.getMealId(),new CartItemBean(meal,1));
		}
		//页面转到shopCart.jsp，显示购物车
		return "shopCart";
	}	
	
	//更改数量
	public String updateSelectedQuantity() throws Exception {
		//从session中取出购物车，放入Map对象cart中
	    Map cart=(Map)session.get("cart");
	    CartItemBean cartItem=(CartItemBean)cart.get(mealId);
	    cartItem.setQuantity(quantity);
	    return "shopCart";
	}
	
	//从购物车中移除指定编号订单
	public String deleteSelectedOrders() throws Exception {
		//从session中取出购物车，放入Map对象cart中
	    Map cart=(Map)session.get("cart");
	    cart.remove(mealId);
	    return "shopCart";
	}	
	
	//清空购物车
	public String clearCart() throws Exception {
		//从session中取出购物车，放入Map对象cart中
	    Map cart=(Map)session.get("cart");
	    cart.clear();
	    return "shopCart";
	}	
	
}
