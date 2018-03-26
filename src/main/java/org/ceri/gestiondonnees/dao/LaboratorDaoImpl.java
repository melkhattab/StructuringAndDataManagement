package org.ceri.gestiondonnees.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.metier.IUserMetier;

public class LaboratorDaoImpl implements ILaboratorDao{


	@PersistenceContext
	private EntityManager  em; 
	
	// add to database 
	public void addLaboratory(Laboratory laboratory) {
		
	}
	
	// consulatation de données 
	public Laboratory getLaboratoryByName(String name) {
		
		return null ;
	}
	
	
}
