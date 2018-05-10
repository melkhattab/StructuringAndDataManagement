package org.ceri.gestiondonnees.controllers;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


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
		/*
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
		*/
	//	xQuery = "for $doc in /Document where contains-word($doc/*/WORD/text(),"+searchValue+")"
	//			+ " return distinct-values($doc/path/text())" ;
		
//		xQuery = "for $doc in /DOCUMENT" + 
//				 "	let $path := $doc/PATH" + 
//				 "	let $words := $doc/*/WORD[text()='"+searchValue+"']" + 
//				 "	let $taille := count($words)" + 
//				 "	for $w in $words" + 
//				 "		let $id := $w/@id/string()" + 
//				 "		let $before := string-join($doc/*/WORD[@id >= '1000' and @id < $id]/text(),\" \")" + 
//				 "	return concat($path,\"|:|\",$w,\"|:|\",$id, $before)";

		xQuery = " for $doc in /DOCUMENT "
				+ "	let $path := $doc/PATH "
				+ "	let $filename := $doc/TITLE "
				+ " let $words := $doc/*/WORD[text()='femme'] "
				+ " let $taille := count($words) "
				+ "	for $word in $words "
				+ " 	let $id := $word/@id "
				+ "	return concat($path,\"::\",$filename,\"::\",$word,\"::\",$id)";
		List<String> result = new ArrayList<String>();
		result = FileInExistDB.getFile(xQuery);
		ArrayList<HashMap<String, ArrayList<String>>> pathAndNbrWords = new ArrayList<HashMap<String,ArrayList<String>>>();
		HashMap<String, ArrayList<String>> infoFile = new HashMap<String, ArrayList<String>>();
		for(String line : result) {
			String[] chaines = new String[4];
			chaines = line.split("::"); // {'path','fileName','word','id'}
			String path = chaines[0];
			String fileName = chaines[1];
			String keyword = chaines[2];
			String id = chaines[3];
			
			ArrayList<String> data = new ArrayList<String>(5); // {'fileName','path','word','beforeContext','afterContext'}
			data.add(fileName);data.add(path);data.add(keyword);
			String beforeQuery =  "let $id := "+id+" let $doc := /DOCUMENT for $word in $doc/*/WORD where $word[@id >= -10+$id and @id < $id] return concat($word,\"\")";
			String afterQuery  =   "let $id := "+id+" let $doc := /DOCUMENT for $word in $doc/*/WORD where $word[@id > $id and @id <= $id+10] return concat($word,\"\")";
			
			StringBuilder contextBefore = new StringBuilder(20);
			StringBuilder contextAfter = new StringBuilder(20);
			
			contextBefore = FileInExistDB.getContext(beforeQuery);
			contextAfter = FileInExistDB.getContext(afterQuery);
			data.add(contextBefore.toString()); // sbuilder1 ==> contextBefore 
			data.add(contextAfter.toString()); // sbuilder2 ==> contextAfter
			infoFile.put(id, data);
			
		}
//		System.out.println("size of words list  : "+(Integer.parseInt(numberWords.get(0)) == 31));
		
	//	System.out.println("path with nbr of words that corresponds  : "+pathAndNbrWords.size());
		
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		model.addAttribute("infoFile", infoFile);
        return "data/searchPage";
    }
	/**
	 * this method is used to find a list of words before and after 
	 * the word searched in order to give a context to the user search 
	 * @param begin, the first word of the context (before and after the word)
	 * @param end, the last word of the context (before and after the word)
	 * @return the context as a string
	 */
	
	
	
}