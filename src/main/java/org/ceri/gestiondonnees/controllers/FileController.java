package org.ceri.gestiondonnees.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileController {

	@Autowired
	private IUserMetier metier ;
	
	@RequestMapping(value="files", method = RequestMethod.GET)
    public String displayAllFiles(Model model) {
		Collection<File> files = metier.getAllFiles();
		model.addAttribute("files", files);
		FileData fileData = new FileData() ; 
		fileData.setCorpus(metier.getAllCorpus());
		model.addAttribute("fileData", fileData );
        return "data/files";
    }
	
	@RequestMapping(value="getFiles", method = RequestMethod.POST)
    public String displayFiles(Model model, FileData fileInf ) {
		String name  = fileInf.getFileName();
		Collection<File> files = metier.getFilesByName(name);
		model.addAttribute("files", files);
        return "data/files";
    }
	@RequestMapping(value = "uploadFile", method = RequestMethod.GET)
	public String addCorpusToDB(Model model) {
		// this controller allows to create a new user account
		model.addAttribute("fileData",new FileData());
		return "forms/uploadFile";
	}
	
	
	@RequestMapping(value = "deleteFile/{id}")
	public String deleteCorpus(@PathVariable("id") int id, Model model) {
		// this controller allows to create a new user account
		metier.deleteCorpus(id) ; 
		return "redirect:/corpus";
	}	
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class)  ; 
			
	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/addFile", method = RequestMethod.POST)
	public  String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				java.io.File dir = new java.io.File(rootPath + "/documents");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				String path = dir.getAbsolutePath()
						+ "/"+ file.getOriginalFilename();
				java.io.File serverFile = new java.io.File(path);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				System.out.println(FilenameUtils.getBaseName(file.getOriginalFilename()+":::"+file.getOriginalFilename()));
				File document = new File(file.getOriginalFilename(), path, FilenameUtils.getBaseName(file.getOriginalFilename()), file.getSize());
				return "redirect:files" ;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} 
		
		return "redirect:files" ;
		
	}
}