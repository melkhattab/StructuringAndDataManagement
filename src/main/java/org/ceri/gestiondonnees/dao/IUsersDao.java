package org.ceri.gestiondonnees.dao;

import java.util.Collection;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.Utilisateur;

public interface IUsersDao {

	// methods concerning users  
	public void addUser(Utilisateur user);
	public Utilisateur getUser(int idUser);
	public Collection<Utilisateur> getAllUsers();
	public void addDroitsToUser(Droits droit, Utilisateur user) ;
	public void addRoleToUser(Role role, Utilisateur user);
	
	// methods concerning roles 
	public void addRole(Role role);
	public Collection<Utilisateur> getUsersByRole(Role role);
	public Collection<Role> getAllRoles();
	
	// methods concerning droits 
	
	public void addDroit(Droits droit);
	public Collection<Droits> getAllDroits();
	public Droits getDroits(Utilisateur user);
	
	
}
