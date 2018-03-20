package org.ceri.gestiondonnees.controllers;

import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class UserController1 {
	
	@Autowired
	private IUserMetier metier ; 
	@Autowired
	private JavaMailSender mailSender ; 
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(Model model, UserAccount accountDetails) {
		// this controller add a user to database 
		User user = metier.getUserByEmail(accountDetails.getEmail());
		if(user == null) {
			if(!accountDetails.getPassword().equals("") && accountDetails.getPassword().equals(accountDetails.getConfPassword())) {
				user = new User( accountDetails.getFirstName(), accountDetails.getLastName(), accountDetails.getEmail(), accountDetails.getPassword());
				metier.addUser(user);
				return "dossier/home";
			}
			else {
				model.addAttribute("errorAccount", "password incorrect") ;
			}
		}
		else
			model.addAttribute("errorAccount", "Email déjà utilisé") ;
		return "createAccount";
	}
	
	/* sending email */
	@RequestMapping(value="/send", method = RequestMethod.GET)
    public void sendMail() {
        // takes input from e-mail form
        String sendTo = "elkhattab.mahmoud@gmail.com";
        String subject = "Test sending e-mail";
        String message = "As3ada laho sabahak\n localhost:8080/gestiondonnes/login";
         
        // creates a simple e-mail object
       
        SimpleMailMessage email = new SimpleMailMessage();
           email.setTo(sendTo);
        email.setSubject(subject);
        email.setText(message);
        
        // sends the e-mail
        mailSender.send(email);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        // forwards to the view named "Result"
        
    }
	@RequestMapping(value="/info", method = RequestMethod.GET)
    public String info() {
        return "dossier/listOfUsers";
    }
	
}
