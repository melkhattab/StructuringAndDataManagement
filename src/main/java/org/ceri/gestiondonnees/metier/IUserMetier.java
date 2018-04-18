package org.ceri.gestiondonnees.metier;

import java.util.Collection;

import org.ceri.gestiondonnees.entities.Permission;
import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;

public interface IUserMetier {
	
		// ===========================  methods concerning users =========================== 
		public void addUser(User user);
		public User getUserByEmail(String email);
		public Collection<User> getAllUsers();
		public void addPermissionToUser(Permission droit, User user);
		public void addRoleToUser(Role role, User user);
		public boolean deleteUser(int id); 
		
		// =========================== methods concerning roles ===========================
		public void addRole(Role role);
		public Role getRoleByLibelle(String libelle);
		public Collection<Role> getAllRoles();
		public boolean deleteRole(String libelle) ;
		
		// =========================== methods concerning droits ==========================
		public void addPermission(Permission permission);
		public Collection<Permission> getAllPermission();
		public Permission getPermission(User user);
		
		// =========================== Methods that manipulate Labortory ==================
		public void addLaboratory(Laboratory laboratory);
		public Laboratory getLaboratoryByName(String name);
		public Collection<Laboratory> getAllLaboratories();
		public boolean deleteLaboratory(int id); 
		
		public void addCorpusToLaboratory(Corpus corpus, Laboratory lab); 
		
		// =========================== Methods that manipulate corpus =====================
		public void addCorpus(Corpus corpus);
		public Collection<Corpus> getAllCorpus();
		public Corpus getCorpusByName(String name);
		public boolean deleteCorpus(int id); 
		
}
