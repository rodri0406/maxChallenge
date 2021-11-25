package com.rmedina.max.challenge.app.controllers;

import java.util.List;

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
public class MarketController extends AbsController {
	

	@Autowired
	private MarketService marketService;
	
	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<Market>> list() {
		return new ResponseEntity<List<Market>>(marketService.list(), HttpStatus.OK);
	
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<Market> read(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<Market>(marketService.read(id), HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		marketService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Market> create(@RequestBody Market market) {
		return new ResponseEntity<Market>(marketService.create(market), HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<Market> update(@RequestBody @Valid Market market) {
		Market marketUpdated = marketService.update(market);	
		return new ResponseEntity<Market>(marketUpdated, HttpStatus.OK);	
	}


}
