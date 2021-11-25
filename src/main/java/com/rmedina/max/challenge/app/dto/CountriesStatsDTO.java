package com.rmedina.max.challenge.app.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountriesStatsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String country;
	private List<Map<String, Object>> market;
	
	public CountriesStatsDTO() {
		market = new ArrayList<>();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void add(Map<String, Object> data) {
		this.market.add(data);
	}

	public List<Map<String, Object>> getMarket() {
		return market;
	}

	public void setMarket(List<Map<String, Object>> market) {
		this.market = market;
	}
	
	
	
	
	

}
