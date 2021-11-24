package com.rmedina.max.challenge.app.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmedina.max.challenge.app.models.dto.CountryDistributionDTO;
import com.rmedina.max.challenge.app.models.services.StatService;

@RestController
@RequestMapping("/stats")
public class StatController {
	
	@Autowired
	private StatService statService;

	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<CountryDistributionDTO> > getDistributionAndCountries() {
		try {
				return new ResponseEntity<List<CountryDistributionDTO> >(statService.getDistributionAndCountries(), HttpStatus.OK);
			
		} catch (Throwable e) {
			return new ResponseEntity<List<CountryDistributionDTO> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
