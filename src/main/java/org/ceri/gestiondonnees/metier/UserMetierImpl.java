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


public class UserMetierImpl implements IUserMetier {
	
	private IUsersDao dao ; 
	
	public void setUsersDao(IUsersDao usersDao) {
		this.dao = usersDao;
	} 
	@Transactional
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
	@Override
	public void addRoleToUser(Role role, User user) {
		dao.addRoleToUser(role, user);
	}
	@Override
	@Transactional
	public boolean deleteUser(int id) {
		return dao.deleteUser(id) ; 
	}
	/* -----------------  Role functionalities  ----------------- */
	@Override
	@Transactional
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
		return dao.getAllRoles();
	}
	@Override
	@Transactional
	public boolean deleteRole(String libelle) {
		return dao.deleteRole(libelle);
	}
	/* =========================================  Permission  ================================================ 
	==========================================================================================================*/
	@Override
	@Transactional
	public void addPermission(Permission permission) {
		// TODO Auto-generated method stub
		dao.addPermission(permission);
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
	@Transactional
	public void addLaboratory(Laboratory laboratory) {
		// TODO Auto-generated method stub
		dao.addLaboratory(laboratory);
	}

	@Override
	public Laboratory getLaboratoryByName(String name) {
		// TODO Auto-generated method stub
		return dao.getLaboratoryByName(name);
	}

	@Override
	public Collection<Laboratory> getAllLaboratories() {
		// TODO Auto-generated method stub
		return dao.getAllLaboratories();
	}
	@Override
	@Transactional
	public boolean deleteLaboratory(int id) {
		
		return  dao.deleteLaboratory(id);
				
	}
	
	@Override
	@Transactional
	public void addCorpusToLaboratory(Corpus corpus, Laboratory lab) {
		// TODO Auto-generated method stub
		
	}

	/* =========================================== corpus methods ===============================================
	   ==========================================================================================================*/
	@Override
	@Transactional
	public void addCorpus(Corpus corpus) {
		// TODO Auto-generated method stub
		dao.addCorpus(corpus);
	}

	@Override
	public Collection<Corpus> getAllCorpus() {
		// TODO Auto-generated method stub
		return dao.getAllCorpus();
	}

	@Override
	public Corpus getCorpusByName(String name) {
		// TODO Auto-generated method stub
		return dao.getCorpusByName(name);
	}
	@Override
	@Transactional
	public boolean deleteCorpus(int id) {
		// TODO Auto-generated method stub
		return dao.deleteCorpus(id);
	}
	
	

}
