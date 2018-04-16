package org.ceri.gestiondonnees.metier;

import java.util.Collection;

import org.apache.log4j.DailyRollingFileAppender;
import org.ceri.gestiondonnees.dao.IUsersDao;
import org.ceri.gestiondonnees.entities.Permission;
import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserMetierImpl implements IUserMetier {
	
	private IUsersDao dao ; 
	
	public void setUsersDao(IUsersDao usersDao) {
		this.dao = usersDao;
	}
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.getUserByEmail(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return dao.getAllUsers();
	}
	@Override
	public void addPermissionToUser(Permission droit, User user) {
		dao.addPermissionToUser(droit,user);
	}
	public void addRoleToUser(Role role, User user) {
		dao.addRoleToUser(role, user);
	}
	/* -----------------  Role functionalities  ----------------- */
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		dao.addRole(role);
	}
	
	@Override
	public Role getRoleByLibelle(String libelle) {
		// TODO Auto-generated method stub
		return dao.getRoleByLibelle(libelle);
	}
	
	@Override
	public Collection<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDroit(Permission droit) {
		// TODO Auto-generated method stub
		dao.addDroit(droit);
	}

	@Override
	public Collection<Permission> getAllPermission() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Permission getPermission(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ========================================= laboratory methods =============================================
	   ==========================================================================================================*/
	
	@Override
	public void addLaboratory(Laboratory laboratory) {
		// TODO Auto-generated method stub
		dao.addLaboratory(laboratory);
	}

	@Override
	public Laboratory getLaboratoryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Laboratory> getAllLaboratories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCorpusToLaboratory(Corpus corpus, Laboratory lab) {
		// TODO Auto-generated method stub
		
	}

	/* =========================================== corpus methods ===============================================
	   ==========================================================================================================*/
	@Override
	public void addCorpus(Corpus corpus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Corpus> getAllCorpus() {
		// TODO Auto-generated method stub
		return dao.getAllCorpus();
	}

	@Override
	public Corpus getCorpusByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
