package org.ceri.gestiondonnees.controllers;


import java.util.Collection;

import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.LoginDetails;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("user")
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
		User user = metier.getUserByEmail(loginDetails.getEmail()) ;
		if(user!= null) {
			if(user.getPassword().equals(loginDetails.getPassword())) {
				model.addAttribute("user", user);
				return "dossier/home";
			}
		}
		model.addAttribute("errorConnection", "email ou mot de passe incorrect");
		return "login";
	}
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountForm(Model model){
		// this controller allows to create a new user account
		model.addAttribute("userAccount", new UserAccount());
		return "createAccount";
	}
	
	// Controller for selecting all users 
	@RequestMapping(value = "/listOfUsers", method = RequestMethod.GET)
	public String listOfUsers(Model model) {
		Collection<User> listOfusers = metier.getAllUsers();
		model.addAttribute("listOfUsers",listOfusers);
		return "dossier/listOfUsers";
	}
	
	
}
