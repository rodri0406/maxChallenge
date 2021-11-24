package com.rmedina.max.challenge.app.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "commitents")
public class Commitent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3514749081643354065L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(name = "commitent_market", joinColumns = @JoinColumn(name = "commitent_id"), inverseJoinColumns = @JoinColumn(name = "market_id"))
	private List<Market> commitentMarkets;
	
	public Commitent() {
		commitentMarkets = new ArrayList<>();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commitent other = (Commitent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Market> getCommitentMarkets() {
		return commitentMarkets;
	}

	public void setCommitentMarkets(List<Market> markets) {
		this.commitentMarkets = markets;
	}
	
	public void add(Market market) {
		this.getCommitentMarkets().add(market);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String body = "";
		body =  this.id + " " + this.description + " ";
		for(Market m : this.getCommitentMarkets()){
			body += m.getDescription();
		};
		return body;
	}


}
