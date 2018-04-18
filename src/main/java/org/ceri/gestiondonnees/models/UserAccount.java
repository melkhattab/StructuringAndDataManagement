package org.ceri.gestiondonnees.models;

import java.util.Collection;
import java.util.List;

import org.ceri.gestiondonnees.entities.Laboratory;

public class UserAccount {
	
	private String firstName ;
	private String lastName ;
	private String email ; 
	private String password ; 
	private String confPassword ;
	private Collection<Laboratory> laboratories ;
	private String selectedLab ; 
	
	
	public String getSelectedLab() {
		return selectedLab;
	}
	public void setSelectedLab(String selectedLab) {
		this.selectedLab = selectedLab;
	}
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
	public Collection<Laboratory> getLaboratories() {
		return laboratories;
	}
	public void setLaboratories(Collection<Laboratory> laboratories) {
		this.laboratories = laboratories;
	}
	
	
	
}
