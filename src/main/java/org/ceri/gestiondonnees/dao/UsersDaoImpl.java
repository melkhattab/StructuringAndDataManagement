package org.ceri.gestiondonnees.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;

public class UsersDaoImpl implements IUsersDao {

	@PersistenceContext
	private EntityManager em ; 
	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public User getUser(int idUser) {
		// TODO Auto-generated method stub
		User  user =  em.find(User.class, idUser);
		return user;
	}
	
	@Override
	public Collection<User> getUsersByRole(Role role) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select u from User u where u.role.idRole = :idRole") ;
		query.setParameter("idRole", role.getIdRole()) ;
		return query.getResultList();
	}
	
	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select u from User u") ;
		return query.getResultList();
	}

	public void addDroitsToUser(Droits droit) {
		
		Droits d = em.find(Droits.class) ;
	}
	
	
	
	
	
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		em.persist(role);

	}

	@Override
	public Collection<Role> getAllRoles() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select r from Role r") ;
		return query.getResultList();
	}

	@Override
	public void addDroit(Droits droit) {
		// TODO Auto-generated method stub
		em.persist(droit);
	}

	@Override
	public Collection<Droits> getAllDroits() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select d from Droits d") ; 
		return query.getResultList();
	}

	@Override
	public Droits getDroits(User user) {
		// TODO Auto-generated method stub
		User u = em.find(User.class, user.getIdUtilisateur());
		Query query = em.createQuery("select u from User d where u.droit = :idDroits and :idUser") ; 
		query.setParameter("idUser", u.getIdUtilisateur()) ;
		return null ;
	}

}
