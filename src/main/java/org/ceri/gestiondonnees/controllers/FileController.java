package org.ceri.gestiondonnees.controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.File;
import org.ceri.gestiondonnees.entities.User;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.CorpusData;
import org.ceri.gestiondonnees.models.FileData;
import org.ceri.gestiondonnees.models.LaboratoryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileController {

	@Autowired
	private IUserMetier metier ;
	
	@RequestMapping(value="files", method = RequestMethod.GET)
    public String displayAllCorpusList(Model model) {
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		FileData fileData = new FileData() ; 
		fileData.setCorpus(metier.getAllCorpus());
		model.addAttribute("fileData", fileData );
        return "data/files";
    }
	
	@RequestMapping(value = "uploadFile", method = RequestMethod.GET)
	public String addCorpusToDB(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("fileData",new FileData());
		return "forms/uploadFile";
	}
	
	@RequestMapping(value = "addFile", method = RequestMethod.POST)
	public String displayCorpusForm(Model model, FileData fileInf, HttpSession session) {
		// this controller allows to create a new user account
		System.out.println("le fichier est : "+fileInf.getFile().getName());
		return "redirect:files";
	}
	
	@RequestMapping(value = "deleteFile/{id}")
	public String deleteCorpus(@PathVariable("id") int id, Model model) {
		// this controller allows to create a new user account
		metier.deleteCorpus(id) ; 
		return "redirect:/corpus";
	}	
}