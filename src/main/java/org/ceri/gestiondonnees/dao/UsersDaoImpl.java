package org.ceri.gestiondonnees.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.ceri.gestiondonnees.entities.Permission;
import org.ceri.gestiondonnees.entities.Laboratory;
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
	public Role getRoleByLibelle(String libelle) {
		// TODO Auto-generated method stub
		try {
			Query query = em.createQuery("select r from Role r where r.libelle = :libelle") ;
			query.setParameter("libelle", libelle);
			return (Role) query.getSingleResult();
		}
		catch(NoResultException exc) {
			return null ;
		}
	}
	
	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		try {
		Query query = em.createQuery("select u from User u") ;
		return query.getResultList();
		}
		catch(NoResultException exc) {
			return null ;
		}
		
	}
	@Override
	public void addPermissionToUser(Permission droit, User user) {
		
		Permission d = em.find(Permission.class,droit) ;
		if(d != null){
			user.setPermission(droit);
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

	/* ---------------------------  Permission  -------------------------*/
	@Override
	public void addDroit(Permission droit) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Permission.findDroit");
		query.setParameter("l", droit.getLire());
		query.setParameter("e", droit.getEcrire());
		query.setParameter("m", droit.getModifier());
		query.setParameter("s", droit.getSupp());
		try {
			Permission d = (Permission) query.getSingleResult();
			if(d != null)
				System.out.println("Droit existe déjà");
		}
		catch(NoResultException ex) {
			em.persist(droit);
		}
		
	}

	@Override
	public Collection<Permission> getAllPermission() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("select d from Permission d") ; 
		return query.getResultList();
	}

	@Override
	public Permission getPermission(User user) {
		// TODO Auto-generated method stub
	//	User u = em.find(User.class, user.getEmail());
	//	Query query = em.createQuery("select u from User d where u.droit = :idDroits and u.email= :email") ; 
	//	query.setParameter("idUser", u.getEmail()) ;
		return null ;
	}
	// methods for laboratory 
	@Override
	public void addLaboratory(Laboratory laboratory) {
		// TODO Auto-generated method stub
		if(laboratory!= null)
			em.persist(laboratory);
	}

}
