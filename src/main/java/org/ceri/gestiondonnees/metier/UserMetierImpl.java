package org.ceri.gestiondonnees.metier;

import java.util.Collection;

import org.ceri.gestiondonnees.dao.IUsersDao;
import org.ceri.gestiondonnees.entities.Droits;
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
	public User getUser(int idUser) {
		// TODO Auto-generated method stub
		return usersDao.getUser(idUser);
	}

	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addDroitsToUser(Droits droit) {
		usersDao.addDroitsToUser(droit);
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

}
