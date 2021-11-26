package com.rmedina.max.challenge.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmedina.max.challenge.app.controllers.CommitentController;
import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.services.CommitentService;

@WebMvcTest(CommitentController.class)
public class CommitentControllerTest {
	
	 @Autowired
	    private MockMvc mvc;

	    @MockBean
	    private CommitentService commitentService;

	    ObjectMapper objectMapper;

	    @BeforeEach
	    void setUp() {
	        objectMapper = new ObjectMapper();
	    }

	    
	    @Test
	    void createTest() throws Exception {
	        when(commitentService.create(any(Commitent.class))).thenReturn(Data.getcommitentMarketsAR());

	        mvc.perform(post("/commitent").contentType(MediaType.APPLICATION_JSON)
	        		.content(objectMapper.writeValueAsString(Data.getCommitentNew())))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.description").value("Soy un commitent 1"));
	    }
	    
	    @Test
	    void readTest() throws Exception {
	        when(commitentService.read(anyLong())).thenReturn(Data.getCommitentMarketUY());

	        mvc.perform(get("/commitent/1").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.description").value("Soy un commitent 2"));
	    }
	    
	    @Test
	    void updateTest() throws Exception {
	        when(commitentService.update(any(Commitent.class))).thenReturn(Data.getCommitentMarketUY());

	        mvc.perform(put("/commitent").contentType(MediaType.APPLICATION_JSON)
      				.content(objectMapper.writeValueAsString(Data.getcommitentMarketsAR())))
	                .andExpect(status().isOk());
	    }
	    
	    @Test
	    void listTest() throws Exception {
	    	List<Commitent> commitents = new ArrayList<>();
	    	commitents.add(Data.getCommitentMarketUY());
	        when(commitentService.list()).thenReturn(commitents);

	        mvc.perform(get("/commitent").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.[0].description").value("Soy un commitent 2"));
	    }

}
