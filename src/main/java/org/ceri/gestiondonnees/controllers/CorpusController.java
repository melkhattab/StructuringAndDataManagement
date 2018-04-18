package org.ceri.gestiondonnees.controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.CorpusData;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CorpusController {

	@Autowired
	private IUserMetier metier ;
	
	@Autowired
	private JavaMailSender mailSender ;

	@RequestMapping(value="corpus", method = RequestMethod.GET)
    public String displayAllCorpusList(Model model) {
		Collection<Corpus> allCorpus = metier.getAllCorpus();
		model.addAttribute("allCorpus", allCorpus);
        return "data/corpus";
    }
	
	@RequestMapping(value = "addCorpus", method = RequestMethod.GET)
	public String addCorpusToDB(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("corpus",new CorpusData());
		return "forms/createCorpus";
	}
	
	@RequestMapping(value = "createCorpus", method = RequestMethod.POST)
	public String displayCorpusForm(Model model, CorpusData corpusInf, HttpSession session) {
		// this controller allows to create a new user account
		Corpus corpus =  new Corpus(corpusInf.getCorpusName(), corpusInf.getDescription(), corpusInf.getCapacity()) ; 
		User user = (User)session.getAttribute("userSession");
		corpus.setUser(user);
		metier.addCorpus(corpus);
		return "redirect:corpus";
	}
	
	@RequestMapping(value = "deleteCorpus/{id}")
	public String deleteCorpus(@PathVariable("id") int id, Model model) {
		// this controller allows to create a new user account
		metier.deleteCorpus(id) ; 
		return "redirect:/corpus";
	}	
}