package com.rmedina.max.challenge.app.models.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.dao.IMarketDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@Service
public class MarketService {

	@Autowired
	private IMarketDao marketDao;
	
	//TODO para esto a una base  de datos o configuraciones
	public static final List<String> countriesAvailable = Arrays.asList("AR", "UY");
	
	
	
	public Market create(Market market) {
		if(countriesAvailable.contains(market.getCountry())){
			return marketDao.save(market);
		} else {
			//TODO cambiar por manejo de exceptions
			return null;
		}
	}
	
	public Optional<Market> read(Long id) {
		//TODO manejar con exceptions
		return marketDao.findById(id);
	}
	
	
	public Market update(Market market) {
		
		if(market.getId() == null ) {
			return null;
		}
		
		Optional<Market> optionalMarket = this.read(market.getId());
		
		if(optionalMarket.isPresent()) {
			return this.create(market);
		}
		
		return null;
	}
	
	public void delete(Long id) {
		marketDao.deleteById(id);
	}
	
	public List<Market> list() {
		return (List<Market>) marketDao.findAll();
	}
	
}
