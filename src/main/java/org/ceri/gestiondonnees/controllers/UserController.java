package org.ceri.gestiondonnees.controllers;


import java.util.Collection;

import org.ceri.gestiondonnees.entities.Droits;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.Utilisateur;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@Autowired
	private IUserMetier metier ; 
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/login")
	public String logInForm(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("userForm",new UserForm());
		return "login";
	}
	
	@RequestMapping(value = "/signin", method=RequestMethod.POST)
	public String sinIn(UserForm userForm, Model model) {
		Utilisateur user = metier.getUser(1) ;
		userForm.setUser(user);
		model.addAttribute("userForm", userForm);
		return "dossier/home";
	}
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountForm(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("userForm", new UserForm());
		return "createAccount";
	}
	
	
}
