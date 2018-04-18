package org.ceri.gestiondonnees.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class User implements Serializable

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7431927710785551534L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private int id ; 
	
	private String email ; 
	private String lastName ;
	private String firstName ; 
	
	private String password ;
	
	@ManyToOne
	@JoinColumn(name="libelle")
	private Role role ; 
	
	@ManyToOne
	@JoinColumn(name="id_permission")
	private Permission permission ;
	
	@ManyToOne
	@JoinColumn(name="id_laborator")
	private Laboratory laboratory ;
	
	@OneToMany(mappedBy="user")
	private Collection<Corpus> corpus ;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User( String firstName,String lastName, String email, String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return this.id;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return this.firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Permission getPermission() {
		return permission;
	}


	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Laboratory getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	} 
	
}
