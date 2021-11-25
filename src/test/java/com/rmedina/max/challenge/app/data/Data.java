package com.rmedina.max.challenge.app.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

public class Data {
	
	public static final Market MARKET_AR = new Market(1L, "FOREX", "Soy el mercado forex", "AR", new HashSet<>(Arrays.asList(
			new Commitent(4l, "Soy un commitent 10"),
			new Commitent(5l, "Soy un commitent 11"),
			new Commitent(6l, "Soy un commitent 12")
			)));
	
	public static final Market MARKET_UY = new Market(4L, "EURO", "Soy el mercado euro", "UY", new HashSet<>(Arrays.asList(
			new Commitent(4l, "Soy un commitent 10"),
			new Commitent(5l, "Soy un commitent 11"),
			new Commitent(6l, "Soy un commitent 12")
			)));
	
	public static final List<Market> MARKETS_AR = Arrays.asList(
			MARKET_AR,
			new Market(2L, "MBR", "Soy el mercado mbr", "AR", new HashSet<>(Arrays.asList(
					new Commitent(4l, "Soy un commitent 10"),
					new Commitent(5l, "Soy un commitent 11")

					)))
	);
	
	public static final List<Market> MARKETS_UY = Arrays.asList(
			new Market(3L, "BASE", "Soy el mercado base", "UY", new HashSet<>(Arrays.asList(
					new Commitent(6l, "Soy un commitent 15"),
					new Commitent(7l, "Soy un commitent 16")
					))),
			MARKET_UY	
	);

	public static final List<Market> MARKETS_AR_COMMITENT_EMPTY = Arrays.asList(
			new Market(2L, "MBR", "Soy el mercado mbr", "AR", new HashSet<>()),
			new Market(4L, "FOREX", "Soy el mercado FOREX", "AR", new HashSet<>())

	);
	
	public static final List<Market> MARKETS_UY_COMMITENT_EMPTY = Arrays.asList(
			new Market(3L, "BASE", "Soy el mercado base", "UY", new HashSet<>()),
			new Market(4L, "ELIC", "Soy el mercado elic", "UY", new HashSet<>())
	);
	
	public static final Set<Market> MARKETS_ALL_SET = Stream.of(MARKETS_AR, MARKETS_UY).flatMap(x -> x.stream()).collect(Collectors.toSet());
	
	public static final List<Market> MARKETS_ALL_LIST = Stream.of(MARKETS_AR, MARKETS_UY).flatMap(x -> x.stream()).collect(Collectors.toList());
	
	
	public static final Commitent COMMITENT_MARKETS_AR = new Commitent(1l, "Soy un commitent 1", new HashSet<>(MARKETS_AR)); 
	
	public static final Commitent COMMITENT_MARKETS_UY = new Commitent(2l, "Soy un commitent 2", new HashSet<>(MARKETS_UY)); 
	
	public static final Commitent COMMITENT_MARKETS_ALL = new Commitent(3l, "Soy un commitent 3", MARKETS_ALL_SET); 

}
