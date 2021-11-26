package com.rmedina.max.challenge.app.models.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@DataJpaTest
public class MarketDaoTest {

	@Autowired
	private IMarketDao marketDao;
	
	
	@Test
	void createTest(){ 
		Market marketSave = Data.getMarketNew();
		
		Market market = marketDao.save(marketSave);
		
		assertNotNull(market);
		assertEquals("Soy el mercado forex", market.getDescription());
		assertEquals("AR", market.getCountry());
		assertTrue(market.getCommitents().isEmpty());
	}
	
	@Test
	void readTest(){ 
		Market marketSave = Data.getMarketNew();
		
		Market market = marketDao.save(marketSave);
		
		Optional<Market> marketOptional = marketDao.findById(market.getId());
		
		assertTrue(marketOptional.isPresent());
		assertEquals("Soy el mercado forex", marketOptional.get().getDescription());
		assertTrue(market.getCommitents().isEmpty());		
	}
	

	@Test
	void updateTest(){ 
		Market marketSave = Data.getMarketNew();
		
		Market market = marketDao.save(marketSave);
		
		market.setDescription(market.getDescription() + " modificado");
		
		Market marketUpdated = marketDao.save(marketSave);
			
		assertNotNull(marketUpdated);
		assertEquals("Soy el mercado forex modificado", marketUpdated.getDescription());
	}
	
	@Test
	void deleteTest(){ 
		Market marketSave = Data.getMarketNew();
		
		Market market = marketDao.save(marketSave);
		
		marketDao.deleteById(market.getId());
	
		Optional<Market> marketOptional = marketDao.findById(market.getId());
		
		assertFalse(marketOptional.isPresent());	
	}
	
	@Test
	void listTest(){ 
		Market market1 = Data.getMarketNew();
		market1.setDescription("market1");
		Market market2 = Data.getMarketNew();
		market2.setDescription("market2");
		Market market3 = Data.getMarketNew();
		market3.setDescription("market3");
		
		marketDao.save(market1);
		marketDao.save(market2);
		marketDao.save(market3);
			
		List<Market> markets = (List<Market>) marketDao.findAll();
		
		assertFalse(markets.isEmpty());
		assertEquals(3, markets.size());
	}
	
	
}
