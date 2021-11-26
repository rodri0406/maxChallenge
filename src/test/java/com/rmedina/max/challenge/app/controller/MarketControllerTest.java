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
import com.rmedina.max.challenge.app.controllers.MarketController;
import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;
import com.rmedina.max.challenge.app.models.services.CommitentService;
import com.rmedina.max.challenge.app.models.services.MarketService;

@WebMvcTest(MarketController.class)
public class MarketControllerTest {
	
	 @Autowired
	    private MockMvc mvc;

	    @MockBean
	    private MarketService marketService;

	    ObjectMapper objectMapper;

	    @BeforeEach
	    void setUp() {
	        objectMapper = new ObjectMapper();
	    }

	    
	    @Test
	    void createTest() throws Exception {
	        when(marketService.create(any(Market.class))).thenReturn(Data.getMarketAR());

	        mvc.perform(post("/market").contentType(MediaType.APPLICATION_JSON)
	        		.content(objectMapper.writeValueAsString(Data.getMarketNew())))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.code").value("FOREX"));
	    }
	    
	    @Test
	    void readTest() throws Exception {
	        when(marketService.read(anyLong())).thenReturn(Data.getMarketAR());

	        mvc.perform(get("/market/1").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.code").value("FOREX"));
	    }
	    
	    @Test
	    void updateTest() throws Exception {
	        when(marketService.update(any(Market.class))).thenReturn(Data.getMarketYU());

	        mvc.perform(put("/market").contentType(MediaType.APPLICATION_JSON)
      				.content(objectMapper.writeValueAsString(Data.getMarketAR())))
	                .andExpect(status().isOk());
	    }
	    
	    @Test
	    void listTest() throws Exception {
	    	List<Market> markets = new ArrayList<>();
	    	markets.add(Data.getMarketAR());
	        when(marketService.list()).thenReturn(markets);

	        mvc.perform(get("/market").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.[0].code").value("FOREX"))
	                .andExpect(jsonPath("$.[0].country").value("AR"));
	    }

}
