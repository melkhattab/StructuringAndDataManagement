package org.ceri.gestiondonnees.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Corpus implements Serializable

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 594106101055429678L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_corpus")
	private int idCorpus ;
	
	private String name ;
	private Long capacity ; 
	private String description ; 
	
	@OneToMany(mappedBy="corpus")
	private Collection<Laboratory> laboratories ;
	
	@OneToMany(mappedBy="corpus")
	private Collection<File> files ;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user ;
	
	public Corpus() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Corpus( String name,String description) {
		super();
		this.name = name ; 
		this.description = description ; 
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCapacity() {
		return capacity;
	}


	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}	
}
