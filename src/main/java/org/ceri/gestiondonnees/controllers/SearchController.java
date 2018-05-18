package org.ceri.gestiondonnees.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


@Controller
@SessionAttributes("queryResult")
public class SearchController {

	@Autowired
	private IUserMetier metier ;
	
	@RequestMapping(value="search", method = RequestMethod.GET)
    public String search(Model model) {
		model.addAttribute("fileData", new FileData());
		return "data/searchPage";
	}
	
	@RequestMapping(value="searchFiles")
    public String searchFiles(Model model, FileData fileData) {
		String criteria   = fileData.getSearchBy();
		String searchValue = fileData.getSearchValue();
		String xQuery  ;
		/*
		if(criteria.equalsIgnoreCase("corpus")) {
			System.out.println("Corpus");
			xQuery = "for $doc in /Document where $doc/Corpus = "+searchValue+" return $doc/path/text()" ;
		}*/

		xQuery = " for $doc in /DOCUMENT "
				+ "	let $path := $doc/PATH "
				+ "	let $filename := $doc/TITLE "
				+ " let $words := $doc/*/WORD[text()='"+searchValue+"'] "
				+ " let $taille := count($words) "
				+ "	for $word in $words "
				+ " 	let $id := $word/@id "
				+ "	return concat($path,\"::\",$filename,\"::\",$word,\"::\",$id)";
		List<String> result = new ArrayList<String>();
		result = FileInExistDB.getFile(xQuery);
		
		HashMap<String, ArrayList<String>> queryResult = new HashMap<String, ArrayList<String>>();
		for(String line : result) {
			String[] chaines = new String[4];
			chaines = line.split("::"); // {'path','fileName','word','id'}
			String path = chaines[0]; 
			String fileName = chaines[1];
			String keyword = chaines[2];
			String id = chaines[3];
			
			ArrayList<String> data = new ArrayList<String>(5); // {'id','fileName','path','word','beforeContext','afterContext'}
			data.add(id);
			data.add(fileName);
			data.add(path);
			data.add(keyword);
			
			
			StringBuilder contextBefore = new StringBuilder(20);
			StringBuilder contextAfter = new StringBuilder(20);
			
			contextBefore = FileInExistDB.getContext(id, true, 15);
			contextAfter = FileInExistDB.getContext(id, true, 15);
			data.add(contextBefore.toString()); // sbuilder1 ==> contextBefore 
			data.add(contextAfter.toString()); // sbuilder2 ==> contextAfter
			queryResult.put(id, data);
			
			
		}
//		System.out.println("size of words list  : "+(Integer.parseInt(numberWords.get(0)) == 31));
//		System.out.println("path with nbr of words that corresponds  : "+pathAndNbrWords.size());
		
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		model.addAttribute("queryResult", queryResult);
        return "data/searchPage";
    }
	
	@RequestMapping(value="extendedContext", method = RequestMethod.GET)
    public String getExtendedContext(Model model, 
						    		@RequestParam("num") int orderNumber,
						    		@RequestParam("name") int fileName,
						    		@RequestParam("id") int wordId, 
						    		HttpSession queryResult ) {
		
//		String contextBefore ; 
//		String contextAfter ;
		
		StringBuilder contextBefore = new StringBuilder(50);
		StringBuilder contextAfter = new StringBuilder(50);
		
		HashMap<String, ArrayList<String>> context = (HashMap<String, ArrayList<String>>) queryResult.getAttribute("queryResult") ;
		if(context != null){
			contextBefore.append(context.get(wordId+"").get(4)) ;
			contextAfter.append(context.get(wordId+"").get(5))  ;
		}
		contextBefore.append(FileInExistDB.getContext(wordId+"",true, 20));
		contextAfter.append(FileInExistDB.getContext(wordId+"",true, 20));
		
		ArrayList<String> data = new ArrayList<String>(5);
		data.add(context.get(wordId+"").get(0));
		data.add(context.get(wordId+"").get(1));
		data.add(context.get(wordId+"").get(2));
		data.add(context.get(wordId+"").get(3));
		data.add(contextBefore.toString());
		data.add(contextAfter.toString());
		context.put(wordId+"", data);
		
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("fileData", new FileData());
		model.addAttribute("queryResult", context);
		return "data/extendedContext";
	}
	
	/**
	 * this method is used to find a list of words before and after 
	 * the word searched in order to give a context to the user search 
	 * @param begin, the first word of the context (before and after the word)
	 * @param end, the last word of the context (before and after the word)
	 * @return the context as a string
	 */	
	
}