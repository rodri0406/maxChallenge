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
import com.rmedina.max.challenge.app.controllers.StatController;
import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.dto.CountriesStatsDTO;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.services.CommitentService;
import com.rmedina.max.challenge.app.models.services.StatService;

@WebMvcTest(StatController.class)
public class StatControllerTest {
	
	 @Autowired
	    private MockMvc mvc;

	    @MockBean
	    private StatService statService;

	    ObjectMapper objectMapper;

	    @BeforeEach
	    void setUp() {
	        objectMapper = new ObjectMapper();
	    }
	    
	    @Test
	    void distributionCountriesTest() throws Exception {
	    	List<CountriesStatsDTO> countryStatList = new ArrayList<>();
	    	CountriesStatsDTO stat = new CountriesStatsDTO();
	    	stat.setCountry("AR");
	    	countryStatList.add(stat);
	    	
	        when(statService.getDistributionAndCountries()).thenReturn(countryStatList);

	        mvc.perform(get("/stats").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.[0].country").value("AR"));
	    }

}
