package com.restrant.biz.impl;

import java.util.List;

import com.restrant.biz.MealBiz;
import com.restrant.dao.MealDAO;
import com.restrant.entity.Meal;
import com.restrant.entity.Pager;

public class MealBizImpl implements MealBiz {

	MealDAO mealDAO;
	public void setMealDAO(MealDAO mealDAO) {
		this.mealDAO = mealDAO;
	}
	
	@Override
	public List getAllMeal(int page) {
		return mealDAO.getAllMeal(page);
	}
	
	@Override
	public Pager getPagerOfMeal() {
		int count= mealDAO.getCountOfAllMeal();
		//使用分页类Pager定义对象
		Pager pager=new Pager();
		//设置pager对象中的perPageRows属性，表示每页显示的记录数
		pager.setPerPageRows(6);
		//设置pager对象中的rowCount属性，表示记录总数
		pager.setRowCount(count);
	    //返回pager对象
		return pager;
	}
	
	/*@Override
	public List getMealBySeries(int mealSeries, int page) {
		return mealDAO.getMealBySeries(mealSeries, page);
	}*/
	/*@Override
	public Pager getPagerOfMeal(int mealSeries) {
		int count= mealDAO.getCountOfMeal(mealSeries);
		//使用分页类Pager定义对象
		Pager pager=new Pager();
		//设置pager对象中的perPageRows属性，表示每页显示的记录数
		pager.setPerPageRows(6);
		//设置pager对象中的rowCount属性，表示记录总数
		pager.setRowCount(count);
	    //返回pager对象
		return pager;
	}*/
	
	@Override
	public List getMealByCondition(Meal condition, int page) {		
		return mealDAO.getMealByCondition(condition, page);
	}
	
	//统计符合查询条件的餐品数量,初始化分页类Pager对象，设置perPageRows和rowCount属性
	@Override
	public Pager getPagerOfMeal(Meal condition) {
		int count= mealDAO.getCountOfMeal(condition);
		//使用分页类Pager定义对象
		Pager pager=new Pager();
		//设置pager对象中的perPageRows属性，表示每页显示的记录数
		pager.setPerPageRows(6);
		//设置pager对象中的rowCount属性，表示记录总数
		pager.setRowCount(count);
	    //返回pager对象
		return pager;
	}
	
	@Override
	public Meal getMealByMealId(int mealId) {
		return mealDAO.getMealByMealId(mealId);
	}
	
	//添加餐品
	@Override
	public void addMeal(Meal meal) {
		mealDAO.addMeal(meal);		
	}
	
	//删除指定编号菜品
	@Override
	public void deleteMeal(int mealId) {		
		Meal meal=mealDAO.getMealByMealId(mealId);
		mealDAO.deleteMeal(meal);
	}

	//修改餐品
	@Override
	public void updateMeal(Meal meal) {
		mealDAO.updateMeal(meal);		
	}
}
