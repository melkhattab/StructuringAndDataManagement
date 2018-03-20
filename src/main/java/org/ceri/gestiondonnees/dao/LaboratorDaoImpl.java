package org.ceri.gestiondonnees.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ceri.gestiondonnees.entities.Laborator;
import org.ceri.gestiondonnees.metier.IUserMetier;

public class LaboratorDaoImpl implements ILaboratorDao{


	@PersistenceContext
	private EntityManager  em; 
	
	// add to database 
	public void addLaborator(Laborator laborator) {
		
	}
	
	// consulatation de données 
	public Laborator getLaboratorByName(String name) {
		
		return null ;
	}
	
	
}
