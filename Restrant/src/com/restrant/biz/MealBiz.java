package com.restrant.biz;

import java.util.List;

import com.restrant.entity.Meal;
import com.restrant.entity.Pager;

public interface MealBiz {
	//获取指定页显示的餐品列表
	public List getAllMeal(int page);
	//获取所有菜品数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	public Pager getPagerOfMeal();	
	
	//获取指定菜系和指定页显示的餐品列表
	//public List getMealBySeries(int mealSeries,int page);
	
	//获取指定菜系的菜品数量,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	//public Pager getPagerOfMeal(int mealSeries);
	
	//根据查询条件，获取指定页显示的餐品列表
	public List getMealByCondition(Meal condition,int page);
	
	//统计符合查询条件的餐品数量,初始化分页类Pager对象，设置perPageRows和rowCount属性
	public Pager getPagerOfMeal(Meal condition);
	
	//根据编号加载菜品
	public Meal getMealByMealId(int mealId);
	
	//添加餐品
	public void addMeal(Meal meal);
	
	//删除指定编号菜品
	public void deleteMeal(int mealId);
	
	//修改餐品
	public void updateMeal(Meal meal);
	
}
