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
@SessionAttributes({"queryResult","orderNumber","wordId","beforeId","afterId"})
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
		int id = 0 ;
		HashMap<String, ArrayList<String>> queryResult = new HashMap<String, ArrayList<String>>();
		for(String line : result) {
			String[] chaines = new String[4];
			chaines = line.split("::"); // {'path','fileName','word','id'}
			String path = chaines[0]; 
			String fileName = chaines[1];
			String keyword = chaines[2];
			id = Integer.parseInt(chaines[3]);
			
			ArrayList<String> data = new ArrayList<String>(5); // {'id','fileName','path','word','beforeContext','afterContext'}
			data.add(id+"");
			data.add(fileName);
			data.add(path);
			data.add(keyword);
			
			StringBuilder contextBefore = new StringBuilder(20);
			StringBuilder contextAfter = new StringBuilder(20);
			
			contextBefore = FileInExistDB.getMoreContext(id+"", true, 15);
			contextAfter = FileInExistDB.getMoreContext(id+"", false, 15);
			data.add(contextBefore.toString()); // sbuilder1 ==> contextBefore 
			data.add(contextAfter.toString()); // sbuilder2 ==> contextAfter
			queryResult.put(id+"", data);
			
			
		}
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		model.addAttribute("queryResult", queryResult);	
		System.out.println("bb   ::   bb  !!  "+id);
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
		contextBefore.append(FileInExistDB.getMoreContext((wordId-15)+"",true, 20));
		if(context != null){
			contextBefore.append(context.get(wordId+"").get(4)) ;
			contextAfter.append(context.get(wordId+"").get(5))  ;
		}
		contextAfter.append(FileInExistDB.getMoreContext((wordId+15)+"",false, 20));
		
		ArrayList<String> data = new ArrayList<String>(5);
		data.add(context.get(wordId+"").get(0));
		data.add(context.get(wordId+"").get(1));
		data.add(context.get(wordId+"").get(2));
		data.add(context.get(wordId+"").get(3));
		data.add(contextBefore.toString());
		data.add(contextAfter.toString());
		context.put(wordId+"", data);
		
		model.addAttribute("fileData", new FileData());
		if(wordId-20 > 0)
			model.addAttribute("beforeId", wordId-35);
		else
			model.addAttribute("beforeId", 0);
		model.addAttribute("afterId", wordId+35);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("wordId", wordId);
		model.addAttribute("queryResult", context);
		System.out.println(wordId-35 +" ::  :: "+(wordId+35));
		return "data/extendedContext";
	}
		
	@RequestMapping(value="redirect", method = RequestMethod.GET)
    public String searchMoreLessContext(Model model, FileData fileData, 
						    		HttpSession queryResult ) {
		String redirect = fileData.getRequestContext();
		if(redirect.toLowerCase().equals("more context")) {
			getMoreContext(model, queryResult);
			return "data/extendedContext";
		}
		else if(redirect.toLowerCase().equals("less context")) {
			getLessContext(model, queryResult);
			return "data/extendedContext";
		}
		return "redirect:data/SearchPage";
		
	}
	
	
	public void getMoreContext( Model model,HttpSession queryResult){
		
		StringBuilder contextBefore = new StringBuilder(50);
		StringBuilder contextAfter = new StringBuilder(50);
		
		HashMap<String, ArrayList<String>> context = (HashMap<String, ArrayList<String>>) queryResult.getAttribute("queryResult") ;
		int beforeId = (Integer) queryResult.getAttribute("beforeId") ;
		int afterId = (Integer) queryResult.getAttribute("afterId") ;
		int orderNumber = (Integer) queryResult.getAttribute("orderNumber");
		int wordId = (Integer) queryResult.getAttribute("wordId");
		
		contextBefore.append(FileInExistDB.getMoreContext(beforeId+"",true, 20));		
		if(context != null){
			contextBefore.append(context.get(wordId+"").get(4)) ;
			contextAfter.append(context.get(wordId+"").get(5))  ;
		}
		contextAfter.append(FileInExistDB.getMoreContext(afterId+"",false, 20));
		
		ArrayList<String> data = new ArrayList<String>(5);
		data.add(context.get(wordId+"").get(0));
		data.add(context.get(wordId+"").get(1));
		data.add(context.get(wordId+"").get(2));
		data.add(context.get(wordId+"").get(3));
		data.add(contextBefore.toString());
		data.add(contextAfter.toString());
		context.put(wordId+"", data);
		
		model.addAttribute("beforeId", beforeId-20);
		model.addAttribute("afterId", afterId+20);
		model.addAttribute("orderNumber", orderNumber);
		model.addAttribute("fileData", new FileData());
		model.addAttribute("queryResult", context);
		System.out.println(beforeId-20 +" ::  :: "+(afterId+20));
		
	}	
	public void getLessContext( Model model, HttpSession queryResult){
		
		HashMap<String, ArrayList<String>> context = (HashMap<String, ArrayList<String>>) queryResult.getAttribute("queryResult") ;
		int orderNumber = (Integer) queryResult.getAttribute("orderNumber");
		int wordId = (Integer) queryResult.getAttribute("wordId");
		String[] before = null ;
		String[] after = null ;
		ArrayList<String> contextBefore = new ArrayList<String>();
		ArrayList<String> contextAfter = new ArrayList<String>();
		if(context != null){
			before = context.get(wordId+"").get(4).replace(" ", "").split(" ") ;
			after = context.get(wordId+"").get(5).replace(" ", "").split(" ") ;
		}
		
		for(int i=20 ; i < before.length ; i++) {
			contextBefore.add(before[i]);
		}
		
		for(int i=0 ; i < (after.length - 20) ; i++) {
			contextAfter.add(after[i]);
		}
		
		System.out.println("vvvvvvvvvvvvv : "+before[0]);
		System.out.println("uuuuuuuuuuuuu : "+after);
		
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
		
	}
}