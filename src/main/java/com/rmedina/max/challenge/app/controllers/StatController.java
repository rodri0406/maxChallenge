package com.rmedina.max.challenge.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmedina.max.challenge.app.dto.CountriesStatsDTO;
import com.rmedina.max.challenge.app.models.services.StatService;

@RestController
@RequestMapping("/stats")
public class StatController extends AbsController{
	
	@Autowired
	private StatService statService;

	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<CountriesStatsDTO> > getDistributionAndCountries() {
		return new ResponseEntity<List<CountriesStatsDTO> >(statService.getDistributionAndCountries(), HttpStatus.OK);
		
	}
	

}
