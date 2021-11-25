package com.rmedina.max.challenge.app.models.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rmedina.max.challenge.app.dto.CountriesStatsDTO;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.dao.IMarketDao;
import com.rmedina.max.challenge.app.models.entities.Market;

@Service
public class StatService {

	@Autowired
	private IMarketDao marketDao;
	
	@Value("${spring.main.countries.available}")
	private List<String> countriesAvailable;
	
	@Autowired
	private ICommitentDao commitentDao;
	
	public List<CountriesStatsDTO>  getDistributionAndCountries() {
		
		List<CountriesStatsDTO> result = new ArrayList<>();
		
		CountriesStatsDTO countriesStats = null;
		
		long commitentSize = commitentDao.count();
		
		List<Market> markets = null;
		
		Map<String, Object> marketResult = null;
		Map<String, Object> percentage = null;
		
		for(String code: countriesAvailable) {
		
			countriesStats = new CountriesStatsDTO();
			marketResult = new HashMap<>();
			
			countriesStats.setCountry(code);
			
			markets = marketDao.findByCountry(code);
			
			for(Market market: markets) {
			
				percentage = new HashMap<>();

				if(commitentSize != 0) {
					percentage.put("percentage", String.format("%.2f", (float)(market.getCommitents().size() * 100) / commitentSize));
				} else {
					percentage.put("percentage", String.format("%.2f",0f));	
				}
				
				marketResult.put(market.getCode(), percentage);
				
			}

			countriesStats.add(marketResult);	
			result.add(countriesStats);
		}
		
		
		return result;
	}

}
