package com.rmedina.max.challenge.app.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Locale.IsoCountryCode;

import org.apache.catalina.DistributedManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.dao.IMarketDao;
import com.rmedina.max.challenge.app.models.dto.CountryDistributionDTO;
import com.rmedina.max.challenge.app.models.entities.Market;

@Service
public class StatService {

	@Autowired
	private IMarketDao marketDao;
	
	@Autowired
	private ICommitentDao commitentDao;
	
	public List<CountryDistributionDTO>  getDistributionAndCountries() {
		
		List<CountryDistributionDTO> result = new ArrayList<>();
		
		CountryDistributionDTO countrydistribution = null;
		long commitentSize = commitentDao.count();
		
		List<Market> marketList = null;
		Map<String, Object> marketResult = null;
		Map<String, Object> percentage = null;
		
		for(String code: MarketService.countriesAvailable) {
		
			countrydistribution = new CountryDistributionDTO();
			marketResult = new HashMap<>();
			
			countrydistribution.setCountry(code);
			
			marketList = marketDao.findByCountry(code);
			
			for(Market market: marketList) {
			
				percentage = new HashMap<>();

				if(commitentSize != 0) {
					percentage.put("percentage", String.format("%.2f", (float)(market.getCommitents().size() * 100) / commitentSize));
				} else {
					percentage.put("percentage", String.format("%.2f",0f));	
				}
				
				marketResult.put(market.getCode(), percentage);
				
			}

			countrydistribution.add(marketResult);	
			result.add(countrydistribution);
		}
		
		
		return result;
	}

}
