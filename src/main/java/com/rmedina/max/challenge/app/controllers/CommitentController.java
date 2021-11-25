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

import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.services.CommitentService;

@RestController
@RequestMapping("/commitent")
public class CommitentController extends AbsController {

	@Autowired
	private CommitentService commitentService;

	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<Commitent> read(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<Commitent>(commitentService.read(id), HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<Commitent>> list() {
		return new ResponseEntity<List<Commitent>>(commitentService.list(), HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		commitentService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Commitent> create(@RequestBody @Valid Commitent commitent) {
		return new ResponseEntity<Commitent>(commitentService.create(commitent), HttpStatus.OK);
	}

	@Transactional
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody @Valid Commitent commitent) {
		commitentService.update(commitent);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
