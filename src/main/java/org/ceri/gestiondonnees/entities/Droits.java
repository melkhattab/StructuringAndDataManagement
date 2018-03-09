package org.ceri.gestiondonnees.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Droits.findDroit", 
			query="select d from Droits d where d.lire = :l and d.ecrire = :e and d.modifier = :m and d.supp = :s")
})
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

	public String getLire() {
		return lire;
	}

	public void setLire(String lire) {
		this.lire = lire;
	}

	public String getEcrire() {
		return ecrire;
	}

	public void setEcrire(String ecrire) {
		this.ecrire = ecrire;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getSupp() {
		return supp;
	}

	public void setSupp(String supp) {
		this.supp = supp;
	}

	public boolean equals(Droits droit) {
		return true ;
	}
	

	
}
