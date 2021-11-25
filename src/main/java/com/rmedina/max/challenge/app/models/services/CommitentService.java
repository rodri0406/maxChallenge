package com.rmedina.max.challenge.app.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmedina.max.challenge.app.exception.NotAssociatedIdException;
import com.rmedina.max.challenge.app.exception.NotExistElementException;
import com.rmedina.max.challenge.app.exception.NotMarketAssociatedException;
import com.rmedina.max.challenge.app.models.dao.ICommitentDao;
import com.rmedina.max.challenge.app.models.entities.Commitent;

@Service
public class CommitentService {

	@Autowired
	private ICommitentDao commitentDao;
	
	
	public Commitent create(Commitent commitent) {

		if(commitent.getCommitentMarkets().isEmpty()) {
			throw new NotMarketAssociatedException("Debe tener al menos un mercado asociado");
		}
		
		return commitentDao.save(commitent);
	}
	
	public Commitent read(Long id) {
		Optional<Commitent> commitentOptional = commitentDao.findById(id);
		
		if(!commitentOptional.isPresent()) {
			throw new NotExistElementException("El elemento no existe");
		}
		
		return commitentOptional.get();
	}
	
	
	public Commitent update(Commitent commitent) {
		if(commitent.getId() == null ) {
			throw new NotAssociatedIdException("El id es inválido");
		}
		
		this.read(commitent.getId());
		
		return this.create(commitent);
	}
	
	public void delete(Long id) {
		this.read(id);
		commitentDao.deleteById(id);
	}
	
}
