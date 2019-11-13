package com.restrant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.restrant.dao.MealSeriesDAO;
import com.restrant.entity.Meal;
import com.restrant.entity.Mealseries;

public class MealSeriesDAOImpl implements MealSeriesDAO {

    SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List getMealSeries() {
		Session session=sessionFactory.getCurrentSession();
		Criteria c=session.createCriteria(Mealseries.class);
		return c.list();
	}

}
