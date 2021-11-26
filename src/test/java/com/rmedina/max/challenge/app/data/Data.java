package com.rmedina.max.challenge.app.data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rmedina.max.challenge.app.models.entities.Commitent;
import com.rmedina.max.challenge.app.models.entities.Market;

public class Data {
	
	public static Market getMarketAR() {
		return new Market(1L, "FOREX", "Soy el mercado forex", "AR", new HashSet<>(Arrays.asList(
	
			new Commitent(4l, "Soy un commitent 10"),
			new Commitent(5l, "Soy un commitent 11"),
			new Commitent(1l, "Soy un commitent 1"),
			new Commitent(6l, "Soy un commitent 12")
			)));
	}
	
	public static Market getMarketYU() {
		return new Market(4L, "EURO", "Soy el mercado euro", "UY", new HashSet<>(Arrays.asList(
	
			new Commitent(4l, "Soy un commitent 10"),
			new Commitent(5l, "Soy un commitent 11"),
			new Commitent(6l, "Soy un commitent 12")
			)));
	}
	
	public static List<Market> getMarketsAR(){
		return Arrays.asList(
	
			getMarketAR(),
			new Market(2L, "MBR", "Soy el mercado mbr", "AR", new HashSet<>(Arrays.asList(
					new Commitent(4l, "Soy un commitent 10"),
					new Commitent(5l, "Soy un commitent 11")

					)))
	);
	}
	
	public static List<Market> getMarketsUY() {
		return Arrays.asList(
	
			new Market(3L, "BASE", "Soy el mercado base", "UY", new HashSet<>(Arrays.asList(
					new Commitent(6l, "Soy un commitent 15"),
					new Commitent(7l, "Soy un commitent 16")
					))),
			getMarketYU()	
	);
	}

	public static List<Market> getMarketARCommitentEmpty() {
		return Arrays.asList(
	
			new Market(2L, "MBR", "Soy el mercado mbr", "AR", new HashSet<>()),
			new Market(4L, "FOREX", "Soy el mercado FOREX", "AR", new HashSet<>())

	);
	}
	
	public static List<Market> getMarketUYCommitentEmpty(){
		return Arrays.asList(
	
			new Market(3L, "BASE", "Soy el mercado base", "UY", new HashSet<>()),
			new Market(4L, "ELIC", "Soy el mercado elic", "UY", new HashSet<>())
	);
	}
	public static Set<Market> getMarketsAllSet(){
		return Stream.of(getMarketsAR(), getMarketsUY()).flatMap(x -> x.stream()).collect(Collectors.toSet());
	}
	
	public static List<Market> getMarketsAllList(){
		return Stream.of(getMarketsAR(), getMarketsUY()).flatMap(x -> x.stream()).collect(Collectors.toList());
	}
	
	
	public static Commitent getcommitentMarketsAR() {
		return new Commitent(1l, "Soy un commitent 1", new HashSet<>(getMarketsAR())); 
	}
	
	public static Commitent getCommitentMarketUY() {
		return new Commitent(2l, "Soy un commitent 2", new HashSet<>(getMarketsUY())); 
	}
	
	public static Commitent getCommietntMarketsAll() {
		return new Commitent(3l, "Soy un commitent 3", getMarketsAllSet()); 
	}
	
	public static Commitent getCommitentNew() {
		return new Commitent(null, "Soy un nuevo commitente", 
				new HashSet<>(Arrays.asList( new Market(1l, "MAGALLANES",  "soy MAGALLANES", "AR",new HashSet<>()))));
	}
	
	public static Market getMarketNew() {
		return new Market(null , "FOREX", "Soy el mercado forex", "AR", new HashSet<>());
	}

}
