package org.ceri.gestiondonnees.metier;

import java.util.Collection;

import org.apache.log4j.DailyRollingFileAppender;
import org.ceri.gestiondonnees.dao.IUsersDao;
import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Laborator;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserMetierImpl implements IUserMetier {
	
	private IUsersDao usersDao ; 
	
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		usersDao.addUser(user);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return usersDao.getUserByEmail(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return usersDao.getAllUsers();
	}
	@Override
	public void addDroitsToUser(Droits droit, User user) {
		usersDao.addDroitsToUser(droit,user);
	}
	public void addRoleToUser(Role role, User user) {
		usersDao.addRoleToUser(role, user);
	}
	/* -----------------  Role functionalities  ----------------- */
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		usersDao.addRole(role);
	}

	@Override
	public Collection<User> getUsersByRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDroit(Droits droit) {
		// TODO Auto-generated method stub
		usersDao.addDroit(droit);
	}

	@Override
	public Collection<Droits> getAllDroits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Droits getDroits(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLaborator(Laborator laborator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Laborator getLaboratorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
