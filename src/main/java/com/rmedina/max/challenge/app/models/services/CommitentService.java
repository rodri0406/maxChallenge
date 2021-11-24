package com.rmedina.max.challenge.app.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;

@Service
public class CommitentService {

	@Autowired
	private ICommitentDao commitentDao;
	
	
	public Commitent create(Commitent commitent) {
		return commitentDao.save(commitent);
	}
	
	public Optional<Commitent> read(Long id) {
		//TODO manejar con exceptions
		return commitentDao.findById(id);
	}
	
	
	public Commitent update(Commitent commitent) {
		//TODO manejar con exceptions
		return this.create(commitent);
	}
	
	public void delete(Long id) {
		commitentDao.deleteById(id);
	}
	
}
