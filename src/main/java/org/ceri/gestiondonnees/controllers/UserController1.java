package org.ceri.gestiondonnees.controllers;

import org.ceri.gestiondonnees.models.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController1 {
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(Model model, UserForm userForm) {
		// this controller add a user to database 
		model.addAttribute("isAccountCreated", true) ;
		return "login";
	}
}
