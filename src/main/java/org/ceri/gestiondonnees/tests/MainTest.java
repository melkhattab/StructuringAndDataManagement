package org.ceri.gestiondonnees.tests;

import java.util.Collection;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.Utilisateur;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserMetier metier = (IUserMetier) context.getBean("businessLogic");
		/* ----------------   écriture ddans la base de données -----------------------*
	
		metier.addRole(new Role("Admin"));
		metier.addRole(new Role("Admin","this is the administrator role"));
		metier.addRole(new Role("Student"));
		metier.addRole(new Role("Professor"));
		
		metier.addDroit(new Droits("oui","oui","oui","oui"));
		metier.addDroit(new Droits("oui","oui","oui","non"));
		metier.addDroit(new Droits("oui","oui","non","non"));
		metier.addDroit(new Droits("oui","non","non","non"));
		metier.addDroit(new Droits("oui","oui","oui","oui"));
		*/
		metier.addUser(new Utilisateur("el khattab", "mahmoud", "machin@gmail.com", "abcde"));
		metier.addUser(new Utilisateur("last name 1", "first name 1", "mail1@gmail.com", "password1"));
		metier.addUser(new Utilisateur("last name 2", "first name 2", "mail2@gmail.com", "password2"));
		System.out.println("tout s'est bien passé");
	
		
	/* ----------------   consultation de données de la base de données -----------------------*/
	//*				
		Utilisateur u = metier.getUser(1);
	//	metier.addDroit(new Droits("oui","oui","oui","oui"));
		
		System.out.println("l'utilisateur est : "+u.getNom()+" "+u.getPrenom());
	//*/
	}

}
