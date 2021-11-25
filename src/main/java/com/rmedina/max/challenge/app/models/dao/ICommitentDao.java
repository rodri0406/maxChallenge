package com.rmedina.max.challenge.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.rmedina.max.challenge.app.models.entities.Commitent;

public interface ICommitentDao extends CrudRepository<Commitent, Long>{
	
	Commitent save(Commitent commitent);

	
}
