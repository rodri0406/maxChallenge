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
import com.rmedina.max.challenge.app.exception.NotExistElementException;
import com.rmedina.max.challenge.app.exception.NotMarketAssociatedException;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;

@SpringBootTest
class CommitentServiceTest {
	
	
	@MockBean
	private ICommitentDao commitentDao;
	
	@Autowired
	private CommitentService commitentService;

	@Test
	void createCommitentEmptyMarketTest() {
		when(commitentDao.save(Data.COMMITENT_MARKETS_AR)).thenReturn(Data.COMMITENT_MARKETS_AR);
		Commitent commitent = Data.COMMITENT_MARKETS_AR;
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
		Commitent commitent = Data.COMMITENT_MARKETS_UY;
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
	
	


}
