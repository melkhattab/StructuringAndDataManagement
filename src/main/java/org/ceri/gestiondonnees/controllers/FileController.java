package org.ceri.gestiondonnees.controllers;

import java.util.Collection;
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
public class FileController {

	@Autowired
	private IUserMetier metier ;
	
	String filesServerLocation = "F:/Workspace JEE/Corpus/";
	
	@RequestMapping(value="files", method = RequestMethod.GET)
    public String displayAllFiles(Model model) throws Exception{
		
		
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		FileData fileData = new FileData() ; 
		fileData.setCorpus(metier.getAllCorpus());
		model.addAttribute("fileData", fileData );
        return "data/files";
    }
	
	@RequestMapping(value="getFiles", method = RequestMethod.POST)
    public String displayFiles(Model model, FileData fileInf ) {
		
		String xQuery = "for $x in /bookstores/book/author[1]/text() return $x";
		FileInExistDB.getFile(xQuery);
		
		String name  = fileInf.getFileName();
		Collection<File> files = metier.getFilesByName(name);
		model.addAttribute("files", files);
        return "data/files";
    }
	
	@RequestMapping(value = "uploadFile", method = RequestMethod.GET)
	public String addCorpusToDB(Model model) {
		// this controller allows to create a new user account
		FileData fileData = new FileData() ; 
		fileData.setCorpus(metier.getAllCorpus());
		model.addAttribute("fileData",fileData);
		return "forms/uploadFile";
	}
	
	@RequestMapping(value = "deleteFile/{id}")
	public String deleteCorpus(@PathVariable("id") int id, Model model) {
		// this controller allows to create a new user account
		metier.deleteFile(id) ; 
		return "redirect:/files";
	}	
		
	/**
	 * 
	 * @param name
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "addFile", method = RequestMethod.POST)
	public  String uploadFileHandler(Model model, @RequestParam("file") MultipartFile file, FileData fileData, HttpSession session) {
		
		if (!file.isEmpty()) {
			try {
				
				String destination = filesServerLocation+"/"+fileData.getSelectedCorpus()+"/"+file.getOriginalFilename();
				fileData.setPath(destination);
				java.io.File directory = new java.io.File(destination);
				
				//corpus associated directory if not exist
				if (!directory.exists())
					directory.mkdirs();
				
				java.io.File fileToStore = new java.io.File(destination);
				file.transferTo(fileToStore);
				
				String fileName   = FilenameUtils.getBaseName(file.getOriginalFilename());
				fileData.setFileName(fileName);
				FileInExistDB.putFile(fileData, "admin","admin");
//				addFileToDataBase(file, fileData);
				
//				MedataDataFileManipulation.put();
				return "redirect:files" ;
			} catch (Exception e) {	
				
				e.printStackTrace();
				System.out.println("nnn");
			}
		} 
		System.out.println();
		return "redirect:files" ;
	}
	
	private String addFileToDataBase(MultipartFile file, FileData fileData) {
		String name   = FilenameUtils.getBaseName(file.getOriginalFilename());
		Long size   = file.getSize();
		String path = fileData.getPath();
		String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
		File existingFile = metier.getFileByName(name);
		if(existingFile!= null && existingFile.getSize().equals(size) && existingFile.getFileType().equals(fileType)) {
			return "le document est déjà existant" ;
		}
		else {
			Corpus selectedCorpus = metier.getCorpusByName(fileData.getSelectedCorpus()) ;
			File document = new File(name, path, fileType , size);
			document.setCorpus(selectedCorpus);
			metier.addFile(document);
			return "le document a été bien ajouté";
		}
	}
	
}