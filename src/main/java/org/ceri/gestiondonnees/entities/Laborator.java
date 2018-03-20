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
public class Laborator implements Serializable

{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_laborator")
	private int idLaborator ; 
	private String name ;
	private String description ; 
	
	@OneToMany(mappedBy="laborator")
	private Collection<User> users ;
	
	@ManyToOne
	@JoinColumn(name="id_corpus")
	private Corpus corpus ;
	
	@ManyToOne
	@JoinColumn(name="id_file")
	private File file ;
	
	public Laborator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Laborator( String name,String description) {
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
