package org.ceri.gestiondonnees.controllers;

import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController1 {
	
	@Autowired
	private IUserMetier metier ; 
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(Model model, UserAccount userDetails) {
		// this controller add a user to database 
		User user = metier.getUserByEmail(userDetails.getEmail());
		if(user == null) {
			if(userDetails.getPassword().equals(userDetails.getConfPassword())) {
				user = new User(userDetails.getLastName(), userDetails.getFirstName(), userDetails.getEmail(), userDetails.getPassword());
				metier.addUser(user);
				return "home";
			}
			else {
				model.addAttribute("errorAccount", "password incorrect") ;
			}
		}
		model.addAttribute("errorAccount", "Email déjà utilisé") ;
		return "createAccount";
	}
}
