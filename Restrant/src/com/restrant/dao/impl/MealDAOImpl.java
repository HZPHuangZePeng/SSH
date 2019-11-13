package com.restrant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.restrant.dao.MealDAO;
import com.restrant.entity.Meal;

public class MealDAOImpl implements MealDAO {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//获取指定页显示的餐品列表
	@Override
	public List getAllMeal(int page) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Meal.class);
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}

	//统计所有餐品总数
	@Override
	public Integer getCountOfAllMeal() {
		Integer count=null;
		try{
		Session session=sessionFactory.getCurrentSession();
		String hql="select count(s) from Meal s";
		Query query=session.createQuery(hql);
		count= Integer.parseInt(query.uniqueResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	/*
	@Override
	public List getMealBySeries(int mealSeries, int page) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Meal.class);
		c.add(Restrictions.eq("mealseries.seriesId", mealSeries));
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}*/

	/*@Override
	public Integer getCountOfMeal(int mealSeries) {
		Integer count=null;
		try{
		Session session=sessionFactory.getCurrentSession();
		String hql="select count(s) from Meal s where mealseries.seriesId="+mealSeries;
		Query query=session.createQuery(hql);
		count= Integer.parseInt(query.uniqueResult().toString()) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}*/

	@Override
	public List getMealByCondition(Meal condition, int page) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Meal.class);
		if(condition!=null){
			if(condition.getMealName()!=null && !condition.getMealName().equals("")){
				//按菜名进行筛选
				c.add(Restrictions.like("mealName", condition.getMealName(),MatchMode.ANYWHERE));
			}
			if((condition.getMealseries()!=null) && (condition.getMealseries().getSeriesId()!=null)){
				//按菜系进行筛选
				c.add(Restrictions.eq("mealseries.seriesId", condition.getMealseries().getSeriesId()));
			}
		}
		c.setFirstResult(6*(page-1));
		c.setMaxResults(6);
		return c.list();
	}

	@Override
	public Integer getCountOfMeal(Meal condition) {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Meal.class);
		if(condition!=null){
			if(condition.getMealName()!=null && !condition.getMealName().equals("")){
				//按菜名进行筛选
				c.add(Restrictions.like("mealName", condition.getMealName(),MatchMode.ANYWHERE));
			}
			if((condition.getMealseries()!=null) && (condition.getMealseries().getSeriesId()!=null)){
				//按菜系进行筛选
				c.add(Restrictions.eq("mealseries.seriesId", condition.getMealseries().getSeriesId()));
			}
		}
		return c.list().size();
	}

	@Override
	public Meal getMealByMealId(int mealId) {
		Session session=sessionFactory.getCurrentSession();
		return (Meal)session.get(Meal.class, mealId);
	}

	//添加餐品
	@Override
	public void addMeal(Meal meal) {
		Session session=sessionFactory.getCurrentSession();
		session.save(meal);
	}

	//删除菜品
	@Override
	public void deleteMeal(Meal meal) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(meal);
	}

	//修改餐品
	@Override
	public void updateMeal(Meal meal) {
		Session session=sessionFactory.getCurrentSession();
		session.update(meal);		
	}	
}
