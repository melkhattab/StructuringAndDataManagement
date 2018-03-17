package org.ceri.gestiondonnees.controllers;


import java.util.Collection;

import org.ceri.gestiondonnees.entities.Utilisateur;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.LoginDetails;
import org.ceri.gestiondonnees.models.UserAccount;
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
		model.addAttribute("loginDetails",new LoginDetails());
		return "login";
	}
	
	@RequestMapping(value = "/signIn", method=RequestMethod.POST)
	public String sinIn(LoginDetails loginDetails, Model model) {
		Utilisateur user = metier.getUser(1) ;
		//userDetails.setUser(user);
		model.addAttribute("ConnectedUser", user);
		return "dossier/home";
	}
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountForm(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("userAccount", new UserAccount());
		return "createAccount";
	}
	
	
}
