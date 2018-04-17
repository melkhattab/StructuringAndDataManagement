package org.ceri.gestiondonnees.models;

public class RoleData {
	
	private String role;
	private String Description ;
	private String errorMessage ; 
	private boolean read ; 
	private boolean write ; 
	private boolean update ; 
	private boolean delete ; 
	
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
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean isWrite() {
		return write;
	}
	public void setWrite(boolean write) {
		this.write = write;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	
}
