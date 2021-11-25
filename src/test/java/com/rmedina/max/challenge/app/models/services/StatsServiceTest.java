package com.rmedina.max.challenge.app.models.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;

import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.dto.CountriesStatsDTO;
import com.rmedina.max.challenge.app.exception.NotExistElementException;
import com.rmedina.max.challenge.app.exception.NotMarketAssociatedException;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.dao.IMarketDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@SpringBootTest
class StatsServiceTest {
	

	@MockBean
	private IMarketDao marketDao;

	@Autowired
	private StatService statService;
	

	@Value("${spring.main.countries.available}")
	private List<String> countriesAvailable;


	@Test
	void distributionCountryCommitentEmptyTest() {
	
		
		doAnswer(args -> {
			String code = args.getArgument(0);
			List<Market> markets;
			switch(code) {
			case "AR":
				markets = Data.MARKETS_AR_COMMITENT_EMPTY;
			case "UY":
				markets = Data.MARKETS_UY_COMMITENT_EMPTY;
			default:
				markets = new ArrayList<>();
				
			}
			return markets;
		}).when(marketDao).findByCountry(anyString());
		
		when(marketDao.countCommitents()).thenReturn(4l);
		
		List<CountriesStatsDTO> distribution = statService.getDistributionAndCountries();
		
		assertFalse(distribution.isEmpty());
		assertTrue(countriesAvailable.contains(distribution.get(0).getCountry()));
		assertTrue(distribution.get(0).getMarket().get(0).isEmpty());
		
	}
	
	@Test
	void distributionCountryCommitentMarketCountryARTest() {
	
		
		doAnswer(args -> {
			String code = args.getArgument(0);
			List<Market> markets;
			switch(code) {
			case "AR":
				markets = Arrays.asList(Data.MARKET_AR);
				break;
			case "UY":
				markets = new ArrayList<>();
				break;
			default:
				markets = new ArrayList<>();
				break;
				
			}
			return markets;
		}).when(marketDao).findByCountry(anyString());
		
		when(marketDao.countCommitents()).thenReturn(3l);
		
		List<CountriesStatsDTO> distribution = statService.getDistributionAndCountries();
		Map<String, String> forexPercentage = (Map<String, String>) distribution.get(0).getMarket().get(0).get("FOREX");
		
		assertFalse(distribution.isEmpty());
		assertEquals("100,00", forexPercentage.get("percentage"));
	}
	

	@Test
	void distributionCountryCommitentMarketCountryARUYTest() {
		
		doAnswer(args -> {
			String code = args.getArgument(0);
			List<Market> markets;
			switch(code) {
			case "AR":
				markets = Arrays.asList(Data.MARKET_AR);
				break;
			case "UY":
				markets = Arrays.asList(Data.MARKET_UY);
				break;
			default:
				markets = new ArrayList<>();
				break;
				
			}
			return markets;
		}).when(marketDao).findByCountry(anyString());
		
		when(marketDao.countCommitents()).thenReturn(6l);
		
		List<CountriesStatsDTO> distribution = statService.getDistributionAndCountries();
		Map<String, String> forexPercentage = (Map<String, String>) distribution.get(0).getMarket().get(0).get("FOREX");
		
		assertFalse(distribution.isEmpty());
		assertEquals("50,00", forexPercentage.get("percentage"));
	}
		

}
