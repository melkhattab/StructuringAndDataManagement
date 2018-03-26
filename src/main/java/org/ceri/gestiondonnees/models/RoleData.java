package org.ceri.gestiondonnees.models;

public class RoleData {
	
	private String role;
	private String Description ;
	private String errorMessage ; 
	
	public String getRole() {
		return role;
	}
	public void setRole(String name) {
		this.role = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
