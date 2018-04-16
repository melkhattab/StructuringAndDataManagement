package org.ceri.gestiondonnees.controllers;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.Role;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
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
public class ProcessingController {
	
	@Autowired
	private IUserMetier metier ; 
	
	@Autowired
	private JavaMailSender mailSender ; 
	
	@RequestMapping(value = "/addLaboratory", method = RequestMethod.POST)
	public String createLaboratory(Model model, LaboratoryData labData) {
		
		Laboratory laboratory = metier.getLaboratoryByName(labData.getName());
		if(laboratory == null && labData.getName()!=null) {
			metier.addLaboratory(new Laboratory(labData.getName(), labData.getDescription()));
			return "data/home";
		}
		else {
			labData.setErrorMessage("Le laboratoire existe déjà");
			model.addAttribute("labData",labData) ;
			return "forms/createLaboratory";
		}
		
	}
	
	
}
