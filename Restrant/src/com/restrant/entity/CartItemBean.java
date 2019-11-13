package com.restrant.entity;

import java.io.Serializable;

public class CartItemBean implements Serializable {
	private Meal meal;
	private int quantity;	
	public Meal getMeal() {
		return meal;
	}
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItemBean(Meal meal, int quantity) {
		this.meal = meal;
		this.quantity = quantity;
	}	

    
}
