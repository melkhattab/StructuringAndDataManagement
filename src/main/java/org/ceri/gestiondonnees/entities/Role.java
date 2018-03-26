package org.ceri.gestiondonnees.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Role.finRole", 
			query="select r from Role r where r.libelle = :libelle")
})
public class Role implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = -6715065949399507405L;
	//	private int idRole ; 
	@Id
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
	public Role(String libelle, String description) {
		super();
		this.libelle = libelle;
		this.description = description ;
		
	}
/*
	public int getIdRole() {
		return idRole;
	}*/

	public String getLibelle() {
		return libelle;
	}


	public String getDescription() {
		return description;
	}

}
