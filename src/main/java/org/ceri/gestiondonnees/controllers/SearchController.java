package org.ceri.gestiondonnees.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.ceri.gestiondonnees.entities.Corpus;
import org.ceri.gestiondonnees.entities.File;
import org.ceri.gestiondonnees.metier.IUserMetier;
import org.ceri.gestiondonnees.models.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class SearchController {

	@Autowired
	private IUserMetier metier ;
	
	@RequestMapping(value="search", method = RequestMethod.GET)
    public String search(Model model) {
		model.addAttribute("fileData", new FileData());
		return "data/searchPage";
	}
	@RequestMapping(value="searchFiles", method = RequestMethod.POST)
    public String searchFiles(Model model, FileData fileData) {
		String criteria   = fileData.getSearchBy();
		String searchValue = fileData.getSearchValue();
		String xQuery  ;
		if(criteria.equalsIgnoreCase("corpus")) {
			System.out.println("Corpus");
			xQuery = "for $doc in /Document where $doc/Corpus = "+searchValue+" return $doc/path/text()" ;
		}
		else {
			if(criteria.equalsIgnoreCase("author")) {
				System.out.println("Author");
				xQuery = "for $doc in /Document where $doc/Author = "+searchValue+" return $doc/path/text()" ;
			}
			else {
				System.out.println("search by Title");
				xQuery = "for $doc in /Document where $doc/Title = "+searchValue+" return $doc/path/text()" ;
			}
		}
		List<String> result = new ArrayList<String>();
		result = FileInExistDB.getFile(xQuery);
		System.out.println("resultat : "+result);
		String name  = fileData.getFileName();
		Collection<File> files = metier.getFilesByName(name);
		model.addAttribute("files", files);
        return "data/searchPage";
    }
	
	
	
}