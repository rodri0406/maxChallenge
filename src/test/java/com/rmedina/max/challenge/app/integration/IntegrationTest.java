package com.rmedina.max.challenge.app.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmedina.max.challenge.app.data.Data;
import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@Autowired
	private TestRestTemplate client;

	private ObjectMapper objectMapper;

	@LocalServerPort
	private int puerto;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	@Order(1)
	void createTest() throws JsonProcessingException {
		Market market = new Market(null, "MAGALLANES", "soy MAGALLANES", "AR", new HashSet<>());

		ResponseEntity<String> response = client.postForEntity(buildURL("/market"), market, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(response.getBody());

		JsonNode marketJson = objectMapper.readTree(response.getBody());

		assertEquals("MAGALLANES", marketJson.path("code").asText());
		assertEquals("AR", marketJson.path("country").asText());

		market.setId(marketJson.path("id").asLong());

		Commitent commitent = Data.getCommitentNew();
		commitent.getCommitentMarkets().clear();
		commitent.getCommitentMarkets().add(market);

		response = client.postForEntity(buildURL("/commitent"), commitent, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(response.getBody());

		JsonNode jsonNode = objectMapper.readTree(response.getBody());

		assertEquals("Soy un nuevo commitente", jsonNode.path("description").asText());

	}
	
	@Test
	@Order(2)
	void readTest() throws JsonProcessingException {

		ResponseEntity<String> response = client.getForEntity(buildURL("/commitent/1"), String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertNotNull(response.getBody());

		JsonNode jsonNode = objectMapper.readTree(response.getBody());

		assertEquals("Soy un nuevo commitente", jsonNode.path("description").asText());

	}

	private String buildURL(String uri) {
		return "http://localhost:" + puerto + uri;
	}

}
