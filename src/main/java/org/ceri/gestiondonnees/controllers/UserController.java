package org.ceri.gestiondonnees.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.CorpusData;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private IUserMetier metier ;
	
	@Autowired
	private JavaMailSender mailSender ;
	
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountForm(Model model){
		// this controller allows to create a new user account
		UserAccount userAccount = new UserAccount();
		List<Laboratory> labs = new ArrayList<Laboratory>();
		labs.add(new Laboratory("LIA", ""));
		labs.add(new Laboratory("LHA", ""));
		
		model.addAttribute("userAccount",userAccount);
		return "forms/createAccount";
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public String createAccount(Model model, UserAccount accountDetails) {
		// this controller add a user to database 
		User user = metier.getUserByEmail(accountDetails.getEmail());
		if(user == null) {
			if(!accountDetails.getPassword().equals("") && accountDetails.getPassword().equals(accountDetails.getConfPassword())){
				user = new User( accountDetails.getFirstName(), accountDetails.getLastName(), 
									accountDetails.getEmail(), accountDetails.getPassword());
				metier.addUser(user); 
				model.addAttribute("errorAccount", "account created") ;
		//		sendMail("elkhattab.mahmoud@gmail.com");
				return "redirect:users";
			}
			else{
				model.addAttribute("errorAccount", "password incorrect") ;
			}
		}
		else
			model.addAttribute("errorAccount", "Email déjà utilisé") ;
		return "forms/createAccount";
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
    public String usersList(Model model) {
		Collection<User> users = metier.getAllUsers();
		model.addAttribute("users", users);
        return "data/usersList";
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
