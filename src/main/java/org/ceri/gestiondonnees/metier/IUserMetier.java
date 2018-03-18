package org.ceri.gestiondonnees.metier;

import java.util.Collection;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;

public interface IUserMetier {
	// methods concerning users  
		public void addUser(User user);
		public User getUserByEmail(String email);
		public Collection<User> getAllUsers();
		public void addDroitsToUser(Droits droit, User user);
		public void addRoleToUser(Role role, User user);
		
		// methods concerning roles 
		public void addRole(Role role);
		public Collection<User> getUsersByRole(Role role);
		public Collection<Role> getAllRoles();
		
		// methods concerning droits 
		
		public void addDroit(Droits droit);
		public Collection<Droits> getAllDroits();
		public Droits getDroits(User user);
		
		
}
