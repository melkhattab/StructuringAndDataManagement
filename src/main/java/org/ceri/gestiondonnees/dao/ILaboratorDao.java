package org.ceri.gestiondonnees.dao;

import org.ceri.gestiondonnees.entities.Laborator;

public interface ILaboratorDao {

	// add to database 
	public void addLaborator(Laborator laborator);
	
	// consulatation de données 
	public Laborator getLaboratorByName(String name);
	
	
}
