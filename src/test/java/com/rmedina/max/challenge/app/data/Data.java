package com.rmedina.max.challenge.app.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

public class Data {
	
	public static final Market MARKET_AR = new Market(1L, "FOREX", "Soy el mercado forex", "AR", new ArrayList<>());
	
	public static final Market MARKET_UY = new Market(4L, "EURO", "Soy el mercado euro", "UY", new ArrayList<>());
	
	public static final List<Market> MARKETS_AR = Arrays.asList(
			MARKET_AR,
			new Market(2L, "MBR", "Soy el mercado mbr", "AR", new ArrayList<>())
	);
	
	public static final List<Market> MARKETS_UY = Arrays.asList(
			new Market(3L, "BASE", "Soy el mercado base", "UY", new ArrayList<>()),
			MARKET_UY	
	);
	
	public static final List<Market> MARKETS_ALL = Stream.of(MARKETS_AR, MARKETS_UY).flatMap(x -> x.stream()).collect(Collectors.toList());;
	
	public static final Commitent COMMITENT_MARKETS_AR = new Commitent(1l, "Soy un commitent", MARKETS_AR); 
	
	public static final Commitent COMMITENT_MARKETS_UY = new Commitent(2l, "Soy un commitent", MARKETS_UY); 
	
	public static final Commitent COMMITENT_MARKETS_ALL = new Commitent(3l, "Soy un commitent", MARKETS_ALL); 

}
