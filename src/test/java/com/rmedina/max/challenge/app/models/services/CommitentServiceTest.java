package com.rmedina.max.challenge.app.models.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;

import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@SpringBootTest
class CommitentServiceTest {
	
	
	//@MockBean
	//private ICommitentDao commitentDao;
	
	@Autowired
	private CommitentService commitentService;

	@Test
	void test() {
		//when(commitentDao.save(Data.COMMITENT_MARKETS_AR)).thenReturn(Data.COMMITENT_MARKETS_AR);
		Commitent commitent = Data.COMMITENT_MARKETS_AR;
		//commitent.setCommitentMarkets(new ArrayList());
		commitentService.create(new Commitent(1L, "Soy el mercado forex", Arrays.asList(
				Data.MARKET_AR,
				new Market(2L, "MBR", "Soy el mercado mbr", "AR", new ArrayList<>())
		)));
		assertEquals(1l, commitent.getId());
	}

}
