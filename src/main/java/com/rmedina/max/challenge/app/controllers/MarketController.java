package com.rmedina.max.challenge.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rmedina.max.challenge.app.models.entities.Market;
import com.rmedina.max.challenge.app.models.services.MarketService;

@RestController
@RequestMapping("/market")
public class MarketController {
	

	@Autowired
	private MarketService marketService;
	
	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<Market>> list() {
		try {
			return new ResponseEntity<List<Market>>(marketService.list(), HttpStatus.OK);
		} catch (Throwable e) {
			return new ResponseEntity<List<Market>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<Market> read(@PathVariable(value = "id") Long id) {
		try {
			Optional<Market> optionalMarket = marketService.read(id);
			if(optionalMarket.isPresent()) {
				return new ResponseEntity<Market>(optionalMarket.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Market>(HttpStatus.NO_CONTENT);
			}
		} catch (Throwable e) {
			return new ResponseEntity<Market>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		try {
			marketService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Throwable e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Market> create(@RequestBody Market market) {
		try {
			Market marketCreated = marketService.create(market);
			if(marketCreated != null) {
				return new ResponseEntity<Market>(marketService.create(market), HttpStatus.OK);
			} else {
				//TODO cambiar por manejo d e excetion apra mostrar msj acorder
				return new ResponseEntity<Market>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Throwable e) {
			return new ResponseEntity<Market>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<Market> update(@RequestBody @Valid Market market) {
		try {
			Market marketUpdated = marketService.update(market);
			
			if(marketUpdated != null ) {
				return new ResponseEntity<Market>(marketUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<Market>(HttpStatus.NO_CONTENT);
			}
		} catch (Throwable e) {
			return new ResponseEntity<Market>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
