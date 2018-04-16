package org.ceri.gestiondonnees.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.CorpusData;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.ceri.gestiondonnees.models.RoleData;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleController {

	@Autowired
	private IUserMetier metier ;

	
	@RequestMapping(value = "/roles/addRole")
	public String addRoleForm(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("roleData",new RoleData());
		return "forms/createRole";
	}
	@RequestMapping(value = "/roles")
	public String rolesList(Model model) {
		// this controller allows to create a new user account
		Collection<Role> roles = metier.getAllRoles();
		model.addAttribute("roles",roles);
		return "data/rolesList";
	}
	

	@RequestMapping(value = "roles/createRole", method = RequestMethod.POST)
	public String createRole(Model model, RoleData roledata) {
		Role role = metier.getRoleByLibelle(roledata.getRole());
		if(role == null && roledata.getRole()!=null) {
			metier.addRole(new Role(roledata.getRole(), roledata.getDescription()));
			return "redirect:roles";
		}
		else {
			roledata.setErrorMessage("Le laboratoire existe déjà");
			model.addAttribute("labData",roledata) ;
			return "forms/createRole";
		}
		
	}
}
