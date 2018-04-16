package org.ceri.gestiondonnees.controllers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.ceri.gestiondonnees.models.LoginDetails;
import org.ceri.gestiondonnees.models.RoleData;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userSession")
public class FormsRender {

	@Autowired
	private IUserMetier metier ;
	
	@Autowired
	private JavaMailSender mailSender ;

	@RequestMapping(value = "/home")
	public String home(HttpSession session) {
		// this controller allows to create a new user account
		if(session.getAttribute("userSession")==null)
			return "redirect:index";
		return "data/home";
	}
	@RequestMapping(value = "/index")
	public String index(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("loginDetails",new LoginDetails());
		return "forms/login";
	}
	@RequestMapping(value="/logout")
    public String logoutForm(HttpServletRequest  request, SessionStatus status) {
		status.setComplete();
        return "redirect:index";
    }
	@RequestMapping(value = "/signIn", method=RequestMethod.POST)
	public String signInForm(User loginDetails, Model model) {
		
		User user = metier.getUserByEmail(loginDetails.getEmail()) ;
		if(user!= null) {
			if(user.getPassword().equals(loginDetails.getPassword())) {
				model.addAttribute("userSession", user);
				return "redirect:home";
			}
		}
		model.addAttribute("errorConnection", "email ou mot de passe incorrect");
		return "redirect:index";
	}
	
	
	
	@RequestMapping(value = "/addLab")
	public String addLaboratoryForm(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("labData",new LaboratoryData());
		return "forms/createLaboratory";
	}
	
	
}
