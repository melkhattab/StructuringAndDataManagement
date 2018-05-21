package org.ceri.gestiondonnees.controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.ceri.gestiondonnees.entities.Laboratory;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private IUserMetier metier ; 
	
	@Autowired
	private JavaMailSender mailSender ;
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
    public String usersList(Model model, HttpSession session) {
		
		if( session.getAttribute("userSession") == null)
			return "redirect:/index";
		Collection<User> users = metier.getAllUsers();
		model.addAttribute("users", users);
        return "data/usersList";
    }
	
	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccountForm(Model model){
		// this controller allows to create a new user account
		UserAccount userAccount = new UserAccount();
		Collection<Laboratory> labs = metier.getAllLaboratories();
		userAccount.setLaboratories(labs);
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
				Laboratory lab = metier.getLaboratoryByName(accountDetails.getSelectedLab());
				user.setLaboratory(lab);
				metier.addUser(user); 
				model.addAttribute("errorAccount", "account created") ;
				//sendMail("elkhattab.mahmoud@gmail.com");
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
	
	@RequestMapping(value = "deleteUser/{id}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable("id") int id, Model model, HttpSession session ) {
		if( session.getAttribute("userSession") == null)
			return "rediret:/index";
		metier.deleteUser(id) ; 
		return "redirect:/users";
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
