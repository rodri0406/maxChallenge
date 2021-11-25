package com.rmedina.max.challenge.app.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "markets")
public class Market implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6010793142485434098L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 100, message = "Supera el máximo de caracteres")
	@NotBlank(message = "No puede  ser vacío")
	private String code;
	
	@Size(max = 200, message = "Supera el máximo de caracteres")
	@NotBlank(message = "No puede  ser vacío")
	private String description;
	
	@Size(max = 50, message = "Supera el máximo de caracteres")
	@NotBlank(message = "No puede  ser vacío")
	private String country;
	
	@JsonIgnore
	@ManyToMany( mappedBy = "commitentMarkets")
	private List<Commitent> commitents;

	
	public Market() {
		// TODO Auto-generated constructor stub
	}
	
	public Market(Long id, String code, String description, String country, List<Commitent> commitents) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.country = country;
		this.commitents = commitents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		Market other = (Market) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<Commitent> getCommitents() {
		return commitents;
	}

	public void setCommitents(List<Commitent> commitents) {
		this.commitents = commitents;
	}
	
	
	

}
