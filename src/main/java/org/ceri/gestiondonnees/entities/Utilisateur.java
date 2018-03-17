package org.ceri.gestiondonnees.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Utilisateur implements Serializable

{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ; 
	
	private String email ; 
	private String nom ;
	private String prenom ; 
	
	private String password ;
	
	@ManyToOne
	@JoinColumn(name="libelle")
	private Role role ; 
	
	@ManyToOne
	@JoinColumn(name="id_droits")
	private Droits droits ;
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Utilisateur(String nom, String prenom, String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Droits getDroits() {
		return droits;
	}


	public void setDroits(Droits droits) {
		this.droits = droits;
	} 
	
	
}
