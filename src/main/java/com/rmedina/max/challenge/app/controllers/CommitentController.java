package com.rmedina.max.challenge.app.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.services.CommitentService;


@RestController
@RequestMapping("/commitent")
public class CommitentController {
	
	
	@Autowired
	private CommitentService commitentService;
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<Commitent> read(@PathVariable(value = "id") Long id) {
		try {
			Optional<Commitent> optionalUser = commitentService.read(id);
			if(optionalUser.isPresent()) {
				return new ResponseEntity<Commitent>(optionalUser.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Commitent>(HttpStatus.NO_CONTENT);
			}
		} catch (Throwable e) {
			return new ResponseEntity<Commitent>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		try {
			commitentService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Throwable e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<Commitent> create(@RequestBody @Valid Commitent commitent) {
		try {
			return new ResponseEntity<Commitent>(commitentService.create(commitent), HttpStatus.OK);
		} catch (Throwable e) {
			return new ResponseEntity<Commitent>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody @Valid Commitent commitent) {
		try {
			commitentService.update(commitent);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Throwable e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//TODO hacer uno individual para cada exception
	@ExceptionHandler(Exception.class)
	  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	  public Map<String, Map<String, String>> requestParamsError(Exception ex) {
	      Map<String, String> map = new HashMap<>();
	      Map<String, Map<String, String>> errors = new HashMap<>();
	      
	      map.put(ex.getClass().getName(), ex.getMessage());
	      
	      errors.put("errores", map);
	      return errors;
	  }

}
