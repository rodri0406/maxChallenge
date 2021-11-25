package com.rmedina.max.challenge.app.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rmedina.max.challenge.app.exception.InvalidCountryException;
import com.rmedina.max.challenge.app.exception.NotAssociatedIdException;
import com.rmedina.max.challenge.app.models.dao.IMarketDao;
import com.rmedina.max.challenge.app.models.entities.Market;

@Service
public class MarketService {

	@Autowired
	private IMarketDao marketDao;
	
	@Value("${spring.main.countries.available}")
	private List<String> countriesAvailable;
	
	
	public Market create(Market market) {
		
		if(!countriesAvailable.contains(market.getCountry())){
			throw new InvalidCountryException("EL país no esta permitido"); 
		}
		
		return marketDao.save(market);
	}
	
	public Market read(Long id) {
		
		Optional<Market> optionalMarket = marketDao.findById(id);
		
		if(!optionalMarket.isPresent()) {
			throw new NotAssociatedIdException("El elemento no existe");
			
		}
		
		return optionalMarket.get();
	}
	
	
	public Market update(Market market) {
		
		if(market.getId() == null ) {
			throw new NotAssociatedIdException("El id es inválido");
		}
		
		this.read(market.getId());
		
		return this.create(market);
	}
	
	public void delete(Long id) {
		this.read(id);
		marketDao.deleteById(id);
	}
	
	public List<Market> list() {
		return (List<Market>) marketDao.findAll();
	}
	
}
