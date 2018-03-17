package org.ceri.gestiondonnees.controllers;

import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController1 {
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(Model model, UserAccount userDetails) {
		// this controller add a user to database 
		System.out.println("firstName: ");
		System.out.println("lastName");
		System.out.println("email");
		System.out.println("password");
		model.addAttribute("isAccountCreated", true) ;
		return "login";
	}
}
