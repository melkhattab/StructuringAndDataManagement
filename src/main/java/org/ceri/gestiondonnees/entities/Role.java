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
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_role")
	private int idRole ; 
	
	private String libelle ; 
	private String description ; 
	
	@OneToMany(mappedBy="role")
	private Collection<User> users ;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	public Role(String libelle) {
		super();
		this.libelle = libelle;
		
	}

	public int getIdRole() {
		return idRole;
	}

	
	
	
	
}
