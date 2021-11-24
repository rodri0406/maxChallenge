package com.rmedina.max.challenge.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rmedina.max.challenge.app.models.entities.Market;

public interface IMarketDao extends CrudRepository<Market, Long>{
	
	List<Market> findByCountry(String country);

}
