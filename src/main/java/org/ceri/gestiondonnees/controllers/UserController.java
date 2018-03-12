package org.ceri.gestiondonnees.controllers;


import org.ceri.gestiondonnees.metier.IUserMetier;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserMetier userMetier ; 
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccount(Model model) {
		// this controller allows to create a new user account
		return "formUserAccount";
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn(Model model) {
		// this controller allows to create a new user account
		return "signIn";
	}
	
	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String homePage(Model model) {
		// this controller allows to create a new user account
		return "signIn";
	}
}
