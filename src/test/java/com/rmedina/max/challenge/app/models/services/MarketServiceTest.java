package com.rmedina.max.challenge.app.models.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;

import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.exception.InvalidCountryException;
import com.rmedina.max.challenge.app.exception.NotExistElementException;
import com.rmedina.max.challenge.app.exception.NotMarketAssociatedException;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.dao.IMarketDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@SpringBootTest
class MarketServiceTest {
	
	
	@MockBean
	private IMarketDao marketDao;
	
	@Autowired
	private MarketService marketService;

	@Test
	void createMarketTest() {
		when(marketDao.save(any(Market.class))).thenReturn(Data.getMarketAR());
		Market market = marketDao.save(Data.getMarketNew());
		assertEquals("Soy el mercado forex", market.getDescription());
		assertEquals("AR", market.getCountry());
		
	}

	@Test
	void createAnotherCountryMarketTest() {
		when(marketDao.save(any(Market.class))).thenReturn(Data.getMarketAR());
		Market market = marketDao.save(Data.getMarketNew());
		market.setCountry("COL");
		
		assertThrows(InvalidCountryException.class, () -> {
			marketService.create(market);			
		});
		
	}
	
	@Test
	void readMarketNotElementTest() {
		when(marketDao.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		assertThrows(NotExistElementException.class, () -> {
			marketService.read(2l);			
		});
	}
	
	@Test
	void updateMarketNotExistTest() {
		Market market = Data.getMarketAR();
		market.setDescription("Modificado");
		when(marketDao.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		when(marketDao.save(any(Market.class))).thenReturn(market);		

		assertThrows(NotExistElementException.class, () -> {
			marketService.update(market);			
		});

	}
		
	@Test
	void deleteMarketNotExistTest() {
		when(marketDao.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		
		assertThrows(NotExistElementException.class, () -> {
			marketService.delete(99l);			
		});
	}
}
