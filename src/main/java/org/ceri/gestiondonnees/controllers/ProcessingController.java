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
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public String createRole(Model model, RoleData roledata) {
		
		Role role = metier.getRoleByLibelle(roledata.getRole());
		if(role == null && roledata.getRole()!=null) {
			metier.addRole(new Role(roledata.getRole(), roledata.getDescription()));
			return "data/home";
		}
		else {
			roledata.setErrorMessage("Le laboratoire existe déjà");
			model.addAttribute("labData",roledata) ;
			return "forms/createRole";
		}
		
	}
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
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(Model model, UserAccount accountDetails) {
		// this controller add a user to database 
		User user = metier.getUserByEmail(accountDetails.getEmail());
		if(user == null) {
			if(!accountDetails.getPassword().equals("") && accountDetails.getPassword().equals(accountDetails.getConfPassword())){
				user = new User( accountDetails.getFirstName(), accountDetails.getLastName(), 
									accountDetails.getEmail(), accountDetails.getPassword());
		//		metier.addUser(user); 
				sendMail("elkhattab.mahmoud@gmail.com");
				return "data/home";
			}
			else{
				model.addAttribute("errorAccount", "password incorrect") ;
			}
		}
		else
			model.addAttribute("errorAccount", "Email déjà utilisé") ;
		return "forms/createAccount";
	}
	
	@RequestMapping(value="/usersList", method = RequestMethod.GET)
    public String usersList(Model model) {
		Collection<User> users = metier.getAllUsers();
		model.addAttribute("users", users);
        return "data/usersList";
    }
	@RequestMapping(value="/corpusList", method = RequestMethod.GET)
    public String corpusLis(Model model) {
		Collection<User> users = metier.getAllUsers();
		model.addAttribute("users", users);
        return "data/corpusList";
    }
	
	/* sending emails */
    public void sendMail(String sendTo) {
        // takes input from e-mail form
                
        String subject = "Test sending e-mail";
        
        StringBuilder message = new StringBuilder();
        message.append("This is my first e-mail using javaMail with spring framework \n\t\t ");
        message.append("Please contact us for need in the following address\n\t localhost:8080/gestiondonnes/login");
        
        // creates a simple e-mail object       
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(sendTo);
        email.setSubject(subject);
        email.setText(message.toString());
        // sends the e-mail
        mailSender.send(email);
    }
	
}
