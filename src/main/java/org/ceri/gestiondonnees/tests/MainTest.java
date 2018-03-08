package org.ceri.gestiondonnees.tests;

import javax.annotation.Resource;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserMetier metier = (IUserMetier) context.getBean("businessLogic");
		
		/* ----------------   écriture ddans la base de données -----------------------*/
	/*
		Role r = new Role("Admin");
		metier.addRole(r);
		metier.addRole(new Role("Student"));
		metier.addRole(new Role("Professor"));
	
		metier.addDroit(new Droits("oui","oui","oui","oui"));
		metier.addDroit(new Droits("oui","oui","oui","non"));
		metier.addDroit(new Droits("oui","oui","non","non"));
		metier.addDroit(new Droits("oui","non","non","non"));
	
		metier.addUser(new User("el khattab", "mahmoud", "machin@gmail.com", "abcde"));
		metier.addUser(new User("last name 1", "first name 1", "mail1@gmail.com", "password1"));
		metier.addUser(new User("last name 2", "first name 2", "mail2@gmail.com", "password2"));
		System.out.println("tout s'est bien passé");
	*/
		
	/* ----------------   consultation de données de la base de données -----------------------*/
		User u = (User)metier.getUser(1);
		System.out.println("l'utilisateur est : "+u.getNom()+" "+u.getPrenom());
	}

}
