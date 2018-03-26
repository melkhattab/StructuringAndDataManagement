package org.ceri.gestiondonnees.models;

import java.util.List;

import org.ceri.gestiondonnees.entities.Laboratory;

public class UserAccount {
	
	private String firstName ;
	private String lastName ;
	private String email ; 
	private String password ; 
	private String confPassword ;
	private String laboratories ;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	public String getLaboratories() {
		return laboratories;
	}
	public void setLaboratories(String laboratories) {
		this.laboratories = laboratories;
	}
	
	
	
}
