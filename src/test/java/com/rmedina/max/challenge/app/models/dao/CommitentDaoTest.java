package com.rmedina.max.challenge.app.models.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.models.entities.Commitent;

@DataJpaTest
public class CommitentDaoTest {

	@Autowired
	private ICommitentDao commitentDao;
	
	
	@Test
	void createTest(){ 
		Commitent commitentSave = Data.getCommitentNew();
		
		Commitent commitent = commitentDao.save(commitentSave);
		
		assertNotNull(commitent);
		assertEquals("Soy un nuevo commitente", commitent.getDescription());
		assertFalse(commitent.getCommitentMarkets().isEmpty());
	}
	
	@Test
	void readTest(){ 
		Commitent commitentSave = Data.getCommitentNew();
		
		Commitent commitent = commitentDao.save(commitentSave);
		
		Optional<Commitent> commitentOptional = commitentDao.findById(commitent.getId());
		
		assertTrue(commitentOptional.isPresent());
		assertEquals("Soy un nuevo commitente", commitentOptional.get().getDescription());
		assertFalse(commitentOptional.get().getCommitentMarkets().isEmpty());
	}
	

	@Test
	void updateTest(){ 
		Commitent commitentSave = Data.getCommitentNew();
		
		Commitent commitent = commitentDao.save(commitentSave);
		
		commitent.setDescription(commitent.getDescription() + " modificado");
		
		Commitent commitentUpdated = commitentDao.save(commitentSave);
			
		assertNotNull(commitentUpdated);
		assertEquals("Soy un nuevo commitente modificado", commitentUpdated.getDescription());
	}
	
	@Test
	void deleteTest(){ 
		Commitent commitentSave = Data.getCommitentNew();
		
		Commitent commitent = commitentDao.save(commitentSave);
		
		commitentDao.deleteById(commitent.getId());
	
		Optional<Commitent> commitentOptional = commitentDao.findById(commitent.getId());
		
		assertFalse(commitentOptional.isPresent());	
	}
	
	@Test
	void listTest(){ 
		Commitent commitent1 = Data.getCommitentNew();
		commitent1.setDescription("commitent1");
		Commitent commitent2 = Data.getCommitentNew();
		commitent2.setDescription("commitent2");
		Commitent commitent3 = Data.getCommitentNew();
		commitent3.setDescription("commitent3");
		
		commitentDao.save(commitent1);
		commitentDao.save(commitent2);
		commitentDao.save(commitent3);
			
	
		List<Commitent> commitents = (List<Commitent>) commitentDao.findAll();
		
		assertFalse(commitents.isEmpty());
		assertEquals(3, commitents.size());
	}
	
	
}
