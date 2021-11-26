package com.rmedina.max.challenge.app.models.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;

import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.exception.NotExistElementException;
import com.rmedina.max.challenge.app.exception.NotMarketAssociatedException;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@SpringBootTest
class CommitentServiceTest {
	
	
	@MockBean
	private ICommitentDao commitentDao;
	
	@Autowired
	private CommitentService commitentService;

	
	@Test
	void createCommitentTest() {
		when(commitentDao.save(any(Commitent.class))).thenReturn(Data.getCommitentMarketUY());
		Commitent commitent = commitentService.create(Data.getCommitentNew());
		assertEquals("Soy un commitent 2", commitent.getDescription());		
	}
	
	@Test
	void readCommitentTest() {
		when(commitentDao.findById(anyLong())).thenReturn(Optional.of(Data.getCommitentMarketUY()));
		Commitent commitent = commitentService.read(1l);
		assertEquals("Soy un commitent 2", commitent.getDescription());		
	}
	
	@Test
	void updateCommitentTest() {
		when(commitentDao.save(any(Commitent.class))).thenReturn(Data.getCommitentMarketUY());
		when(commitentDao.findById(anyLong())).thenReturn(Optional.of(Data.getCommitentMarketUY()));	
		Commitent commitent = commitentService.update(Data.getCommitentMarketUY());
		assertEquals("Soy un commitent 2", commitent.getDescription());		
	}
	
	@Test
	void createCommitentEmptyMarketTest() {
		when(commitentDao.save(Data.getcommitentMarketsAR())).thenReturn(Data.getcommitentMarketsAR());
		Commitent commitent = Data.getcommitentMarketsAR();
		commitent.setCommitentMarkets(new HashSet<>());
		assertThrows(NotMarketAssociatedException.class, () -> {
			commitentService.create(commitent);			
		});
		
	}
	
	@Test
	void readCommitentNotElementTest() {
		when(commitentDao.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		assertThrows(NotExistElementException.class, () -> {
			commitentService.read(2l);			
		});
	}
	
	@Test
	void updateCommitentNotExistTest() {
		Commitent commitent = Data.getCommitentMarketUY();
		when(commitentDao.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		commitent.setDescription("Modificado");
		when(commitentDao.save(any(Commitent.class))).thenReturn(commitent);		

		assertThrows(NotExistElementException.class, () -> {
			commitentService.update(commitent);			
		});

	}
		
	@Test
	void deleteCommitentNotExistTest() {
		when(commitentDao.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		
		assertThrows(NotExistElementException.class, () -> {
			commitentService.delete(99l);			
		});

	}
	
	@Test
	void listTest(){ 
		List<Commitent> commitents = (List<Commitent>) commitentDao.findAll();
		assertTrue(commitents.isEmpty());
	}
	


}
