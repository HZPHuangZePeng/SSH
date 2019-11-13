package com.restrant.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Mealseries entity. @author MyEclipse Persistence Tools
 */

public class Mealseries implements java.io.Serializable {

	// Fields

	private Integer seriesId;
	private String seriesName;
	private Set meals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Mealseries() {
	}

	/** full constructor */
	public Mealseries(String seriesName, Set meals) {
		this.seriesName = seriesName;
		this.meals = meals;
	}

	// Property accessors

	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public Set getMeals() {
		return this.meals;
	}

	public void setMeals(Set meals) {
		this.meals = meals;
	}

}