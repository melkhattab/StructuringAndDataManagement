package org.ceri.gestiondonnees.dao;

import java.util.Collection;

import org.ceri.gestiondonnees.entities.Permission;
import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;

public interface IUsersDao {

	// methods concerning users  
	public void addUser(User user);
	public User getUserByEmail(String email); 
	public Collection<User> getAllUsers();
	public void addPermissionToUser(Permission droit, User user) ;
	public void addRoleToUser(Role role, User user);
	
	// methods concerning roles 
	public void addRole(Role role);
	public Role getRoleByLibelle(String libelle);
	public Collection<Role> getAllRoles();
	public boolean deleteRole(String libelle) ;
	
	// methods concerning droits 
	
	public void addPermission(Permission permission);
	public Collection<Permission> getAllPermission();
	public Permission getPermission(User user);
	
	//methods for laboratory entity 
	public void addLaboratory(Laboratory laboratory);
	
	/* Methods that manipulate corpus */
	public void addCorpus(Corpus corpus);
	public Collection<Corpus> getAllCorpus();
	public Corpus getCorpusByName(String name);
	
	
}
