package org.ceri.gestiondonnees.controllers;

import java.util.Collection;

import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.CorpusData;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CorpusController {

	@Autowired
	private IUserMetier metier ;
	
	@Autowired
	private JavaMailSender mailSender ;
	
	
	@RequestMapping(value = "/addCorpus")
	public String addCorpusForm(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("labData",new LaboratoryData());
		return "forms/corpus";
	}
	
	@RequestMapping(value = "/createCorpus")
	public String addCorpusToDB(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("corpus",new CorpusData());
		return "forms/createCorpus";
	}
	
	@RequestMapping(value="/corpus", method = RequestMethod.GET)
    public String displayAllCorpusList(Model model) {
		Collection<Corpus> allCorpus = metier.getAllCorpus();
		model.addAttribute("allCorpus", allCorpus);
        return "data/corpus";
    }
}
