package org.ceri.gestiondonnees.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Droits implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dr")
	private int idDroits ;
	
	private String lire ; 
	private String ecrire ; 
	private String modifier ; 
	private String supp ;
	
	@OneToMany(mappedBy="droits")
	private Collection<User> users ;
	
	public Droits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Droits(String read, String write, String update, String delete) {
		super();
		this.lire = read;
		this.ecrire = write;
		this.modifier = update;
		this.supp = delete;
	}

	public boolean equals(Droits droit) {
		return true ;
	}
	

	
}
