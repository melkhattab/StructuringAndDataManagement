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
public class Laboratory implements Serializable

{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1193144086612867313L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_laborator")
	private int idLaborator ; 
	private String name ;
	private String description ; 
	
	@OneToMany(mappedBy="laboratory")
	private Collection<User> users ;
	
	@ManyToOne
	@JoinColumn(name="id_corpus")
	private Corpus corpus ;
	
	public Laboratory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Laboratory( String name,String description) {
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
