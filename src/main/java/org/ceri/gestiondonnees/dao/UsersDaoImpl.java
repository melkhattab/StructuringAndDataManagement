package org.ceri.gestiondonnees.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;

public class UsersDaoImpl implements IUsersDao {

	@PersistenceContext
	private EntityManager em ; 
	
	/* ------------------------------  User   ---------------------------------------*/
	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		try {
		Query query = em.createQuery("select u from User u where u.email = :email") ;
		query.setParameter("email",email) ;
		return (User) query.getSingleResult();
		}catch(NoResultException ex) {
			return null ;
		}
	}
	
	@Override
	public Collection<User> getUsersByRole(Role role) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select u from User u where u.role.idRole = :idRole") ;
		query.setParameter("idRole", role.getLibelle()) ;
		return query.getResultList();
	}
	
	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select u from User u") ;
		return query.getResultList();
	}
	@Override
	public void addDroitsToUser(Droits droit, User user) {
		
		Droits d = em.find(Droits.class,droit) ;
		if(d != null){
			user.setDroits(droit);
			em.merge(user);
		}
	}
	public void addRoleToUser(Role role, User user) {
		Role r = em.find(Role.class, role.getLibelle());
		if(r!= null) {
			user.setRole(role);
			em.merge(user);
		}
	}
	
	/* ------------------------------  Role   ---------------------------------------*/
	
	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		Role r = em.find(Role.class, role.getLibelle());
		if(r == null)
			em.persist(role);
		else
			System.out.println("le role existe déjà");

	}

	@Override
	public Collection<Role> getAllRoles() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select r from Role r") ;
		return query.getResultList();
	}

	/* ---------------------------  Droits  -------------------------*/
	@Override
	public void addDroit(Droits droit) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Droits.findDroit");
		query.setParameter("l", droit.getLire());
		query.setParameter("e", droit.getEcrire());
		query.setParameter("m", droit.getModifier());
		query.setParameter("s", droit.getSupp());
		try {
			Droits d = (Droits) query.getSingleResult();
			if(d != null)
				System.out.println("Droit existe déjà");
		}
		catch(NoResultException ex) {
			em.persist(droit);
		}
		
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
	//	User u = em.find(User.class, user.getEmail());
	//	Query query = em.createQuery("select u from User d where u.droit = :idDroits and u.email= :email") ; 
	//	query.setParameter("idUser", u.getEmail()) ;
		return null ;
	}

}
