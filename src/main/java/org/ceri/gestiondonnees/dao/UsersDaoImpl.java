package org.ceri.gestiondonnees.dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.ceri.gestiondonnees.entities.Permission;
import org.ceri.gestiondonnees.entities.Corpus;
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
	
	@Override
	public boolean deleteRole(String libelle) {
		try {
				em.remove(em.find(Role.class, libelle));
				em.flush(); em.clear();
				return true ; 
		}
		catch(Exception e) {
			return false ; 
		}
	}

	/* ---------------------------  Permission  -------------------------*/
	@Override
	public void addPermission(Permission permission) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Permission.findPermission");
		 
		query.setParameter("r", permission.getReadPermission());
		query.setParameter("w", permission.getWritePermission());
//		query.setParameter("u", permission.isUpdate());
//		query.setParameter("d", permission.isDelete());
		try {
			Permission d = (Permission) query.getSingleResult();
			if(d != null)
				System.out.println("Permission existe déjà");
		}
		catch(NoResultException ex) {
			em.persist(permission);
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

	/* =========================================== corpus methods ===============================================
	   ==========================================================================================================*/
	
	@Override
	public void addCorpus(Corpus corpus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Corpus> getAllCorpus() {
		try {
			Query query = em.createQuery("select c from Corpus c") ;
			return query.getResultList();
			}
			catch(NoResultException exc) {
				return null ;
			}
	}

	@Override
	public Corpus getCorpusByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
