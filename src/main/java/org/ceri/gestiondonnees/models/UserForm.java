package org.ceri.gestiondonnees.models;

import org.ceri.gestiondonnees.entities.Utilisateur;

public class UserForm {
	private Utilisateur user ;
	private String email ; 
	private String password ; 
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
